package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ServiceException {
    public CustomerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "고객을 찾을 수 없습니다.");
    }
}
