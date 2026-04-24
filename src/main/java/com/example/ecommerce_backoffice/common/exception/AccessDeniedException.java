package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ServiceException {
    public AccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");
    }
}
