package com.example.Shopping_portal.Dto.RequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {

    int product_id;      // which product to add
    String emailId;     // to check which cart to add
    int requiredQuantity; // how much to add

}
