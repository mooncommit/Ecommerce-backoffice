package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class AdminAlreadyProcessedException extends ServiceException {
    public AdminAlreadyProcessedException() {
        super(HttpStatus.BAD_REQUEST, "이미 처리된 관리자입니다.");
    }
}
