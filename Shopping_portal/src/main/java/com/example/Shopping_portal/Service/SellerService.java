package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.SellerRequestDTO;
import com.example.Shopping_portal.Dto.ResponseDto.SellerResponseDto;
import com.example.Shopping_portal.Model.Seller;
import com.example.Shopping_portal.Repository.SellerRepository;
import com.example.Shopping_portal.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDTO sellerRequestDTO){
       // dto -> entitiy
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDTO);
        Seller savedSeller =sellerRepository.save(seller);
        return SellerTransformer.SellerToSellerResponseDTO(savedSeller);
    }
}
