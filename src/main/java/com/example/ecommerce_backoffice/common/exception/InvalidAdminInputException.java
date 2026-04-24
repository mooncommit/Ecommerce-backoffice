package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidAdminInputException extends ServiceException {
    public InvalidAdminInputException() {
        super(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다.");
    }
}
