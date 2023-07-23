package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.CardRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.CardResponseDto;
import com.example.Shopping_portal.Exceptions.CustomerNotFoundException;
import com.example.Shopping_portal.Model.Card;
import com.example.Shopping_portal.Model.Customer;
import com.example.Shopping_portal.Repository.CustomerRepository;
import com.example.Shopping_portal.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto){
        // check if customer exist
       //CardRequestDto check = cardRequestDto;
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobile());
        if(customer == null) {
            throw new CustomerNotFoundException("No such customer with this Mobile No");
        }
        // dto -> entity
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);
        List<Card> cards = savedCustomer.getCards();
        Card latestCard = cards.get(cards.size()-1);

        // entity - > dto
         CardResponseDto responseDto = CardTransformer.CardTocardResponseDto(latestCard);
         responseDto.setCardNo(generateMaskedCard(latestCard.getCardNo()));
         return responseDto;
    }
    public String generateMaskedCard(String cardNo){
        char [] arr = cardNo.toCharArray();
        int size = cardNo.length();
        for(int i=size-4 ; i>=0;i--){
            arr[i] = '*';
        }
        return arr.toString();
    }
}
