package com.example.Shopping_portal.Dto.ResponseDto;


import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerResponseDto {
    String name;
    String emailId;
    String panNo;
}
