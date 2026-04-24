package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidRoleException extends ServiceException {
    public InvalidRoleException() {
        
        super(HttpStatus.BAD_REQUEST, "승인되지 않은 관리자입니다.");
    }
}
