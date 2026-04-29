package com.example.ecommerce_backoffice.order.dto;

import com.example.ecommerce_backoffice.order.entity.Order;
import com.example.ecommerce_backoffice.order.enums.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderStatusUpdateResponseDto {

    private final Long id;
    private final OrderStatus status;

    public static OrderStatusUpdateResponseDto from(Order order) {
        return new OrderStatusUpdateResponseDto(
                order.getId(),
                order.getStatus()
        );
    }
}
