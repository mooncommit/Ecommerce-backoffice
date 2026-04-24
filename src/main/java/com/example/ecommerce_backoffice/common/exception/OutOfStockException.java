package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class OutOfStockException extends ServiceException {
    public OutOfStockException() {
        super(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");
    }
}
