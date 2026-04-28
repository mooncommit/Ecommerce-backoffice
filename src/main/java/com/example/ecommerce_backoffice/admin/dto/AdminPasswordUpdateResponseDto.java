package com.example.ecommerce_backoffice.admin.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 비밀번호 변경 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminPasswordUpdateResponseDto {

    private final String message;
}
