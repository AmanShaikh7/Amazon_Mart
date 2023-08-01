package com.example.Shopping_portal.Transformer;

import com.example.Shopping_portal.Dto.ResponseDto.CartResponseDto;
import com.example.Shopping_portal.Dto.ResponseDto.ItemResponseDto;
import com.example.Shopping_portal.Model.Cart;
import com.example.Shopping_portal.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartReponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}
