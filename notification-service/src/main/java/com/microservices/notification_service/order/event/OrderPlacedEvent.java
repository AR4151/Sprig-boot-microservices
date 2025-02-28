package com.microservices.notification_service.order.event;

//package com.microservices.notification_service.order.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {

    private String orderNumber;
    private String skuCode;
    private String email;
}
