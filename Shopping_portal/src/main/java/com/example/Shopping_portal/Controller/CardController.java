package com.example.Shopping_portal.Controller;

import com.example.Shopping_portal.Dto.RequestDto.CardRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.CardResponseDto;
import com.example.Shopping_portal.Exceptions.CustomerNotFoundException;
import com.example.Shopping_portal.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) {
        try{
        CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
        return new ResponseEntity(cardResponseDto,HttpStatus.CREATED);
    }catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
