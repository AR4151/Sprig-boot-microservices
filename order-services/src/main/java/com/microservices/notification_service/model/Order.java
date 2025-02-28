package com.microservices.notification_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="order_o")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
    private String email;

}
