package com.example.Shopping_portal.Dto.RequestDto;

import com.example.Shopping_portal.Enum.ProductCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
        String email; // email of the seller who will add this product
        String productName;
        int price;
        int availableQuantity;
        ProductCategory category;
}
