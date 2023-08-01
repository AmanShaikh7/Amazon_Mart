package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.ItemRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.ItemResponseDto;
import com.example.Shopping_portal.Exceptions.CustomerNotFoundException;
import com.example.Shopping_portal.Exceptions.LessQuantityAvailableException;
import com.example.Shopping_portal.Exceptions.ProductNotFoundException;
import com.example.Shopping_portal.Model.Customer;
import com.example.Shopping_portal.Model.Item;
import com.example.Shopping_portal.Model.Product;
import com.example.Shopping_portal.Repository.CustomerRepository;
import com.example.Shopping_portal.Repository.ProductRepository;
import com.example.Shopping_portal.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    public Item addItem(ItemRequestDto itemRequestDto){
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getEmailId());
        if(customer == null) throw new CustomerNotFoundException("No such customer !");

        Optional<Product> optioanlproduct = productRepository.findById(itemRequestDto.getProduct_id());
        if(optioanlproduct.isEmpty()) throw new ProductNotFoundException("No product exsit with this product Id");
        Product product = optioanlproduct.get();

        if(product.getAvailableQuantity() < itemRequestDto.getRequiredQuantity()) throw new LessQuantityAvailableException("reduce the number of quantity");

        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto);
       // item.setProduct(product); ///
        return item;
    }
}
