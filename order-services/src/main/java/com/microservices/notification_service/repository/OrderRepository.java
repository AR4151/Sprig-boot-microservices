package com.microservices.notification_service.repository;

import com.microservices.notification_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
