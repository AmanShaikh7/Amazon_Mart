package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.SellerRequestDTO;
import com.example.Shopping_portal.Dto.ResponseDto.SellerResponseDto;
import com.example.Shopping_portal.Model.Product;
import com.example.Shopping_portal.Model.Seller;
import com.example.Shopping_portal.Repository.ProductRepository;
import com.example.Shopping_portal.Repository.SellerRepository;
import com.example.Shopping_portal.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;
    public SellerResponseDto addSeller(SellerRequestDTO sellerRequestDTO){
       // dto -> entitiy
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDTO);
        Seller savedSeller =sellerRepository.save(seller);
        return SellerTransformer.SellerToSellerResponseDTO(savedSeller);
    }

    public List<SellerResponseDto> sellerWithCheapestprice() {
        List<Product> products = productRepository.findAllWithCheapestPrice();
        List<Seller> sellers = new ArrayList<>();
        for(Product p : products){
            if(!sellers.contains(p.getSeller())) sellers.add(p.getSeller());
        }
        List<SellerResponseDto> sellerResponse = new ArrayList<>();
        for(Seller s : sellers){
            sellerResponse.add(SellerTransformer.SellerToSellerResponseDTO(s));
        }
        return sellerResponse;
    }
}
