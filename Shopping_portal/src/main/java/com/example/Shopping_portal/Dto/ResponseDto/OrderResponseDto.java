package com.example.Shopping_portal.Dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String customerName;
    String orderId;
    int orderTotal;
    List<ItemResponseDto> itemResponseDtoList;
}
