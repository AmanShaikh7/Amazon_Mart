package com.example.Shopping_portal.Dto.ResponseDto;

import com.example.Shopping_portal.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String itemName;

    int itemPrice;

    int quantityAdded;

    ProductCategory category;

}
