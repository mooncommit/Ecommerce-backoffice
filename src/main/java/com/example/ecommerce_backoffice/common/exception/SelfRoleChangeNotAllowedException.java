package com.example.ecommerce_backoffice.common.exception;

import org.springframework.http.HttpStatus;

public class SelfRoleChangeNotAllowedException extends ServiceException {
    public SelfRoleChangeNotAllowedException() {
        super(HttpStatus.BAD_REQUEST, "자신의 역할은 변경할 수 없습니다.");
    }
}
