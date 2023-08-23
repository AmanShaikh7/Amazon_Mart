package com.example.Shopping_portal.Dto.RequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    String customerEmail;
    int product_id;
    String cardUsed;
    int cvv;
    int reqQuantity;
}
