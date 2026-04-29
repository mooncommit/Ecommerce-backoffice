package com.example.ecommerce_backoffice.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderCreateRequestDto {

    @NotNull(message = "고객 ID는 필수입니다.")
    private Long customerId;

    @NotNull(message = "상품 ID는 필수입니다.")
    private Long productId;

    @Min(value = 1, message = "수량은 1이상이어야 합니다.")
    private int quantity;

    private boolean csOrder;
}
