package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.CustomerRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.CustomerResponseDto;
import com.example.Shopping_portal.Model.Cart;
import com.example.Shopping_portal.Model.Customer;
import com.example.Shopping_portal.Repository.CustomerRepository;
import com.example.Shopping_portal.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        // dto -> entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);   // saves both customer and cart;
        // prepare the response dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
