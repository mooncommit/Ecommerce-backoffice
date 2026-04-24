package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends ServiceException {
    public PasswordMismatchException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
    }
}