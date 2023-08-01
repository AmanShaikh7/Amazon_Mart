package com.example.Shopping_portal.Controller;

import com.example.Shopping_portal.Dto.RequestDto.ItemRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.CartResponseDto;
import com.example.Shopping_portal.Model.Item;
import com.example.Shopping_portal.Service.CartService;
import com.example.Shopping_portal.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            Item item = itemService.addItem(itemRequestDto);
            // update the cart , i.e save item to cart
            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto,item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);

        }catch (RuntimeException re){
            return new ResponseEntity(re.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
