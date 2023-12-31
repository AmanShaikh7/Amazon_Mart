package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.ProductRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.ProductResponseDto;
import com.example.Shopping_portal.Enum.ProductCategory;
import com.example.Shopping_portal.Enum.ProductStatus;
import com.example.Shopping_portal.Exceptions.SellerNotFoundException;
import com.example.Shopping_portal.Model.Product;
import com.example.Shopping_portal.Model.Seller;
import com.example.Shopping_portal.Repository.ProductRepository;
import com.example.Shopping_portal.Repository.SellerRepository;
import com.example.Shopping_portal.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){

        // check if seller exisit
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getEmail());
        if(seller == null){
            throw new SellerNotFoundException("Seller with this Email, does not exist !");
        }
        // dto -> entity
        Product product = ProductTransformer.productrequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        //product.setProductStatus(ProductStatus.AVAILABLE);
        seller.getProducts().add(product);
        Seller savedSeller = sellerRepository.save(seller);

        List<Product> products = savedSeller.getProducts();
        Product latestProduct = products.get(products.size()-1);
        return  ProductTransformer.productToproductresponseDto(latestProduct);
    }

    public List<ProductResponseDto> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category) {

        List<Product> products = productRepository.getProdByCategoryAndPriceGreaterThan(price,category);

        List<ProductResponseDto> ans = new ArrayList<>();
        for(Product product : products){
            ans.add(ProductTransformer.productToproductresponseDto(product));
        }
        return ans;
    }

    public List<ProductResponseDto> top5cheapestProduct(ProductCategory category) {
        List<Product> filteredProducts = productRepository.findTop5ByCategoryOrderByPriceAsc(category);
        List<ProductResponseDto> reponseList = new ArrayList<>();
        for(Product p : filteredProducts){
            reponseList.add(ProductTransformer.productToproductresponseDto(p));
        }
        return reponseList;
    }
}
