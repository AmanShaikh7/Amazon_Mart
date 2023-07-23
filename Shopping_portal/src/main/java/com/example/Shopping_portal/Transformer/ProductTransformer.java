package com.example.Shopping_portal.Transformer;

import com.example.Shopping_portal.Dto.RequestDto.ProductRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.ProductResponseDto;
import com.example.Shopping_portal.Enum.ProductStatus;
import com.example.Shopping_portal.Model.Product;
import com.example.Shopping_portal.Service.ProductService;

public class ProductTransformer {

    public static Product productrequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .category(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto productToproductresponseDto(Product product){
       // return  ProductResponseDto.builder()
        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .category(product.getCategory())
                .availableQuantity(product.getAvailableQuantity())
                .build();
    }
}
