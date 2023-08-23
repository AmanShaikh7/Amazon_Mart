package com.example.Shopping_portal.Controller;

import com.example.Shopping_portal.Dto.RequestDto.SellerRequestDTO;
import com.example.Shopping_portal.Dto.ResponseDto.SellerResponseDto;
import com.example.Shopping_portal.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get seller(s) with cheapeast price
    @GetMapping("/cheapestPriceSeller")
    public ResponseEntity sellerWithCheapestprice(){
        List<SellerResponseDto> sellers = sellerService.sellerWithCheapestprice();
        return new ResponseEntity(sellers,HttpStatus.FOUND);
    }
}
