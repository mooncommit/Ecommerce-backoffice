package com.example.ecommerce_backoffice.order.repository;

import com.example.ecommerce_backoffice.order.entity.Order;
import com.example.ecommerce_backoffice.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrder(Order order);
}
