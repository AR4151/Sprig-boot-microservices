package com.microservices.notification_service.dto;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price,Integer quantity,String email) {
}

/*What is a record in Java?
A record is a special type of immutable class introduced in Java 14 (preview) and Java 16 (official). It is mainly used
for storing data without writing boilerplate code like constructors, getters, equals(), hashCode(), and toString().*/

/*If you don't use this record and want to create it then the class would look like this
public final class OrderRequest {
    private final Long id;
    private final String orderNumber;
    private final String skuCode;
    private final BigDecimal price;
    private final Integer quantity;
    private final String email;

    public OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity, String email) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
        this.email = email;
    }

    public Long id() { return id; }
    public String orderNumber() { return orderNumber; }
    public String skuCode() { return skuCode; }
    public BigDecimal price() { return price; }
    public Integer quantity() { return quantity; }
    public String email() { return email; }

    @Override
    public boolean equals(Object o)
    @Override
    public int hashCode() {
    @Override
    public String toString() {
}

* */
