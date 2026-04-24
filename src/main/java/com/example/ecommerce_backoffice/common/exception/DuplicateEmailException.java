package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ServiceException {
    public DuplicateEmailException() {
        super(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다.");
    }
}
