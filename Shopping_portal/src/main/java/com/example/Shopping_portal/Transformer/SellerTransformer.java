package com.example.Shopping_portal.Transformer;

import com.example.Shopping_portal.Dto.RequestDto.SellerRequestDTO;
import com.example.Shopping_portal.Dto.ResponseDto.SellerResponseDto;
import com.example.Shopping_portal.Model.Seller;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller (SellerRequestDTO sellerRequestDTO){
        return Seller.builder()
                .name(sellerRequestDTO.getName())
                .emailId(sellerRequestDTO.getEmailId())
                .panNo(sellerRequestDTO.getPanNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDTO(Seller savedSeller) {
        return SellerResponseDto.builder()
                .name(savedSeller.getName())
                .emailId(savedSeller.getEmailId())
                .panNo(savedSeller.getPanNo())
                .build();
    }
}
