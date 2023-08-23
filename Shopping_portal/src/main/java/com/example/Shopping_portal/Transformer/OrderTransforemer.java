package com.example.Shopping_portal.Transformer;

import com.example.Shopping_portal.Dto.ResponseDto.ItemResponseDto;
import com.example.Shopping_portal.Dto.ResponseDto.OrderResponseDto;
import com.example.Shopping_portal.Model.Item;
import com.example.Shopping_portal.Model.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderTransforemer {


    public static OrderResponseDto orderResponseDtoToOrderEntity(OrderEntity orderEntity){
        List<ItemResponseDto> item = new ArrayList<>();
        for(Item i : orderEntity.getItems() ){
            item.add(ItemTransformer.ItemToItemResponseDto(i));
        }

        return OrderResponseDto.builder()
                .customerName(orderEntity.getCustomer().getName())
                .orderId(orderEntity.getOrderId())
                .orderTotal(orderEntity.getOrderTotal())
                .itemResponseDtoList(item)
                .build();
    }

}
