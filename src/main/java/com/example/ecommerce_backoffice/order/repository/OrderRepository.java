package com.example.ecommerce_backoffice.order.repository;

import com.example.ecommerce_backoffice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
