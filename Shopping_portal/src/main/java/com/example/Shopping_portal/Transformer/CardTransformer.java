package com.example.Shopping_portal.Transformer;

import com.example.Shopping_portal.Dto.RequestDto.CardRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.CardResponseDto;
import com.example.Shopping_portal.Model.Card;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
    public static CardResponseDto CardTocardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .validTill(card.getValidTill())
                .cardType(card.getCardType())
                .build();
    }
}
