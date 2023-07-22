package com.example.Shopping_portal.Controller;

import com.example.Shopping_portal.Dto.RequestDto.SellerRequestDTO;
import com.example.Shopping_portal.Dto.ResponseDto.SellerResponseDto;
import com.example.Shopping_portal.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addseller(@RequestBody SellerRequestDTO sellerResponseDTO){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerResponseDTO);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }
}
