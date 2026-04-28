package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidAdminStatusException extends ServiceException {
    public InvalidAdminStatusException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
