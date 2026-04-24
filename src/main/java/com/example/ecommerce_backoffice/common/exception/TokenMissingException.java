package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class TokenMissingException extends ServiceException {
    public TokenMissingException() {
        super(HttpStatus.UNAUTHORIZED, "인증 토큰이 없습니다.");
    }
}
