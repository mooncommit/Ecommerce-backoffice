package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class OrderItemNotFoundException extends ServiceException {
    public OrderItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, "주문 아이템을 찾을 수 없습니다.");
    }
}
