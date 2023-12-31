package com.example.Shopping_portal.Transformer;

import com.example.Shopping_portal.Dto.RequestDto.ItemRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.ItemResponseDto;
import com.example.Shopping_portal.Model.Item;

public class ItemTransformer {

    public static Item itemRequestDtoToItem(int reqQuantity){
        return Item.builder()
                .requiredQuantity(reqQuantity)
                .build();
    }
    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getProductName())
                .quantityAdded(item.getRequiredQuantity())
                .category(item.getProduct().getCategory())
                .build();
    }


}
