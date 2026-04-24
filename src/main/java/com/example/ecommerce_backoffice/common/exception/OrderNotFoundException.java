package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ServiceException {
    public OrderNotFoundException() {
        super(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다.");
    }
}
