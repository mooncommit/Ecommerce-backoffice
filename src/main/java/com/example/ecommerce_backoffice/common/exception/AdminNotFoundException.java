package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends ServiceException{
    public AdminNotFoundException() {
        super(HttpStatus.NOT_FOUND, "관리자를 찾을 수 없습니다.");
    }
}
