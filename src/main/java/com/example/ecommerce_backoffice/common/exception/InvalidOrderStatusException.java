package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidOrderStatusException extends ServiceException {
    public InvalidOrderStatusException() {
        super(HttpStatus.BAD_REQUEST, "주문 상태를 변경할 수 없습니다.");
    }
}
