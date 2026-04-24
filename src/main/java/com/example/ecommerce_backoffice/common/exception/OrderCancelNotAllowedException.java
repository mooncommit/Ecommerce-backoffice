package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class OrderCancelNotAllowedException extends ServiceException {
    public OrderCancelNotAllowedException() {
        super(HttpStatus.BAD_REQUEST, "준비중 상태에서만 취소할 수 있습니다.");
    }
}
