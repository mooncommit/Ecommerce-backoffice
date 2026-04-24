package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class AdminNotApprovedException extends ServiceException {
    public AdminNotApprovedException() {
        super(HttpStatus.UNAUTHORIZED, "승인되지 않은 관리자입니다.");
    }
}
