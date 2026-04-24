package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ServiceException {
    public InvalidPasswordException() {
        super(HttpStatus.BAD_REQUEST, "이메일 또는 비밀번호가 올바르지 않습니다.");
    }
}
