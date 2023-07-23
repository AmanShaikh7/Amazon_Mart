package com.example.Shopping_portal.Controller;

import com.example.Shopping_portal.Dto.RequestDto.ProductRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.ProductResponseDto;
import com.example.Shopping_portal.Enum.ProductCategory;
import com.example.Shopping_portal.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto,HttpStatus.CREATED);
        }catch (RuntimeException re){
            return  new ResponseEntity(re.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get_by_category_and_price_greater_than")
    public  ResponseEntity getProdByCategoryAndPriceGreaterThan(@RequestParam int price, @RequestParam ProductCategory category){

        List<ProductResponseDto> productResponseDtoList = productService.getProdByCategoryAndPriceGreaterThan(price,category);
        return new ResponseEntity(productResponseDtoList,HttpStatus.OK);
    }
}
