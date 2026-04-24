package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ServiceException {
    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다.");

    }
}
