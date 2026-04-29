package com.example.ecommerce_backoffice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {

    private final int status;
    private final String message;
    private final T data;

    // 성공
    public static <T> ApiResponseDto<T> success(HttpStatus status, String message, T data) {
        return new ApiResponseDto<>(status.value(), message, data);
    }

    // 실패
    public static <T> ApiResponseDto<T> fail(HttpStatus status, String message) {
        return new ApiResponseDto<>(status.value(), message, null);
    }
}
