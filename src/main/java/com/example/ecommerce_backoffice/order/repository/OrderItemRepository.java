package com.example.ecommerce_backoffice.order.repository;

import com.example.ecommerce_backoffice.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
