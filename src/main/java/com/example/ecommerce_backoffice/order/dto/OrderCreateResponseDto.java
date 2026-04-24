package com.example.ecommerce_backoffice.order.dto;

import com.example.ecommerce_backoffice.order.entity.Order;
import com.example.ecommerce_backoffice.order.entity.OrderItem;
import com.example.ecommerce_backoffice.order.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class OrderCreateResponseDto {

    private final Long id;
    private final String orderNumber;
    private final String customerName;
    private final String productName;
    private final int quantity;
    private final int totalPrice;
    private final OrderStatus status;
    private final LocalDateTime orderedAt;

    public static OrderCreateResponseDto from(Order order, OrderItem orderItem) {
        return new OrderCreateResponseDto(
                order.getId(),
                order.getOrderNumber(),
                order.getCustomer().getName(),
                orderItem.getProductName(),
                orderItem.getQuantity(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
