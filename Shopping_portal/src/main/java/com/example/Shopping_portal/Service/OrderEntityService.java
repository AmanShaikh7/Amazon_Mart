package com.example.Shopping_portal.Service;

import com.example.Shopping_portal.Dto.RequestDto.OrderRequestDto;
import com.example.Shopping_portal.Dto.ResponseDto.OrderResponseDto;
import com.example.Shopping_portal.Enum.ProductStatus;
import com.example.Shopping_portal.Exceptions.CustomerNotFoundException;
import com.example.Shopping_portal.Exceptions.InvalidCardException;
import com.example.Shopping_portal.Exceptions.LessQuantityAvailableException;
import com.example.Shopping_portal.Exceptions.ProductNotFoundException;
import com.example.Shopping_portal.Model.*;
import com.example.Shopping_portal.Repository.CardRepository;
import com.example.Shopping_portal.Repository.CustomerRepository;
import com.example.Shopping_portal.Repository.OrderRepository;
import com.example.Shopping_portal.Repository.ProductRepository;
import com.example.Shopping_portal.Transformer.ItemTransformer;
import com.example.Shopping_portal.Transformer.OrderTransforemer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderEntityService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CardService cardService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    OrderTransforemer orderTransforemer;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer == null) throw new CustomerNotFoundException("Check Customer email and Try Again !");

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProduct_id());
        if(optionalProduct.isEmpty()) throw  new ProductNotFoundException("No such product with this product id !");

        Product product = optionalProduct.get();

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date currentDate = new Date();
        if(card == null || card.getCvv() != orderRequestDto.getCvv() || currentDate.after(card.getValidTill())) throw new InvalidCardException("Your card is invalid");

        if(orderRequestDto.getReqQuantity() > product.getAvailableQuantity()) throw new LessQuantityAvailableException("We dont have that much qunatity currently !");


        product.setAvailableQuantity(product.getAvailableQuantity() - orderRequestDto.getReqQuantity());
        if(product.getAvailableQuantity() == 0 ) product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generateMaskedCard(orderRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(orderRequestDto.getReqQuantity() * product.getPrice());

        Item item = ItemTransformer.itemRequestDtoToItem(orderRequestDto.getReqQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        orderEntity.getItems().add(item);
        orderEntity.setCustomer(customer);

        OrderEntity savedOrder = orderRepository.save(orderEntity);

        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        //  send email
        sendEmail(savedOrder);

//        customerRepository.save(customer);
//        productRepository.save(product);

        // prepare reposne dto

        return OrderTransforemer.orderResponseDtoToOrderEntity(savedOrder);
    }
    public void sendEmail(OrderEntity order){
        String text = "Congrats your order has been placed!, following are the details :'\n' + Order Id : "+order.getOrderId()
        + '\n'
        +"Order Total :"+order.getOrderTotal()
        +"Order Date :" + order.getOrderDate();


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(order.getCustomer().getEmailId());
        mail.setFrom("amazon.orders.services.delivery@gmail.com");
        mail.setSubject("Order for Iphone 14 placed successfully ");
        mail.setText(text);
        javaMailSender.send(mail);
    }

    public List<OrderResponseDto> topFiveorders() {
        List<OrderEntity> list = orderRepository.findTop5ByOrderByOrderTotalDesc();
        // now i need to convert this order list in order dto list
        List<OrderResponseDto> orders = new ArrayList<>();
        for(OrderEntity order: list){
            orders.add(orderTransforemer.orderResponseDtoToOrderEntity(order));
        }
        return orders;
    }
}
