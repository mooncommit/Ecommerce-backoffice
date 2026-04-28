package com.example.ecommerce_backoffice.order.dto;

import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.order.entity.Order;
import com.example.ecommerce_backoffice.order.entity.OrderItem;
import com.example.ecommerce_backoffice.order.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class OrderDetailResponseDto {

    private final Long id;
    private final String orderNumber;
    private final String customerName;
    private final String customerEmail;
    private final String productName;
    private final int quantity;
    private final int totalPrice;
    private final LocalDateTime orderedAt;
    private final OrderStatus status;
    private String adminName;
    private String adminEmail;
    private AdminRole adminRole;

    public static OrderDetailResponseDto from(Order order, OrderItem orderItem) {
        OrderDetailResponseDto dto = new OrderDetailResponseDto(
            order.getId(),
            order.getOrderNumber(),
            order.getCustomer().getName(),
            order.getCustomer().getEmail(),
            orderItem.getProductName(),
            orderItem.getQuantity(),
            order.getTotalPrice(),
            order.getCreatedAt(),
            order.getStatus()
        );

        if (order.getAdmin() != null) {
            dto.adminName = order.getAdmin().getName();
            dto.adminEmail = order.getAdmin().getEmail();
            dto.adminRole = order.getAdmin().getRole();
        }

        return dto;
    }
}
