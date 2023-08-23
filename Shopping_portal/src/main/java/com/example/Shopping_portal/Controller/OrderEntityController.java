package com.example.Shopping_portal.Controller;

import com.example.Shopping_portal.Dto.RequestDto.OrderRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.OrderResponseDto;
import com.example.Shopping_portal.Service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderEntityController {

    @Autowired
    OrderEntityService orderEntityService;
    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        try {
            OrderResponseDto orderResponseDto = orderEntityService.placeOrder(orderRequestDto);
            return  new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        } catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/topFive")
    public ResponseEntity topFiveorders(){
        List<OrderResponseDto> orderResponseDtos =  orderEntityService.topFiveorders();
        return  new ResponseEntity(orderResponseDtos,HttpStatus.OK);
    }
}
