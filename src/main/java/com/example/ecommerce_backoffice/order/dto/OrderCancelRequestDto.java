package com.example.ecommerce_backoffice.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderCancelRequestDto {

    @NotBlank(message = "취소 사유는 필수입니다.")
    private String cancelReason;
}
