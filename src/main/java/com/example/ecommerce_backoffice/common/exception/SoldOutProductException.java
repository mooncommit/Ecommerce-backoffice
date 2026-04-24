package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class SoldOutProductException extends ServiceException {
    public SoldOutProductException() {
        super(HttpStatus.BAD_REQUEST, "품절된 상품은 주문할 수 없습니다.");
    }
}
