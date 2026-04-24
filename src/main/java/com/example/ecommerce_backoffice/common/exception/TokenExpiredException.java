package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends ServiceException {
    public TokenExpiredException() {
        super(HttpStatus.UNAUTHORIZED, "인증 토큰이 만료되었습니다.");
    }
}
