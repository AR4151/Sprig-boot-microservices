package com.microservices.notification_service.service;

import com.microservices.notification_service.client.InventoryClient;
import com.microservices.notification_service.dto.OrderRequest;
import com.microservices.notification_service.order.event.OrderPlacedEvent;
import com.microservices.notification_service.model.Order;
import com.microservices.notification_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryClient inventoryClient;
    @Autowired
    private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    //This KafkaTemplate is used in a producer class to send messages to Kafka topics.


    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());

        // map OrderRequest to Order object
        if(isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            order.setEmail(orderRequest.email());
            // save the order to OrderRepository
            orderRepository.save(order);

            // Send the Message to Kafka Topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(),order.getSkuCode(),order.getEmail());
            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed",orderPlacedEvent);
            kafkaTemplate.send("order-placed",orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed",orderPlacedEvent);

        }
        else {
            throw new RuntimeException("Product with SkuCode "+orderRequest.skuCode()+" is Not In Stock");
        }



    }
}
