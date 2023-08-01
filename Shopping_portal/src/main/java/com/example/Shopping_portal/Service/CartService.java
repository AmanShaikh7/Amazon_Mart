package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.ItemRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.CartResponseDto;
import com.example.Shopping_portal.Model.Cart;
import com.example.Shopping_portal.Model.Customer;
import com.example.Shopping_portal.Model.Item;
import com.example.Shopping_portal.Model.Product;
import com.example.Shopping_portal.Repository.CartRepository;
import com.example.Shopping_portal.Repository.CustomerRepository;
import com.example.Shopping_portal.Repository.ItemRepository;
import com.example.Shopping_portal.Repository.ProductRepository;
import com.example.Shopping_portal.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartRepository cartRepository;

    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item){

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getEmailId());
        Product product = productRepository.findById(itemRequestDto.getProduct_id()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + product.getPrice() * itemRequestDto.getRequiredQuantity());

        item.setCart(cart);
        item.setProduct(product);

        Item saveditem = itemRepository.save(item);

        cart.getItems().add(saveditem);
        product.getItems().add(saveditem);
        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        //prepare cartResponse Dto
        return CartTransformer.CartToCartReponseDto(savedCart);
    }
}
