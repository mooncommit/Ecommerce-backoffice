package com.example.ecommerce_backoffice.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 관리자 거부 요청 DTO
@Getter
@NoArgsConstructor
public class AdminRejectRequestDto {

    @NotBlank(message = "거부 사유는 필수 입력입니다.")
    private String rejectionReason;
}
