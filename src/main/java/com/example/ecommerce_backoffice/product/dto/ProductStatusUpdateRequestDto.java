package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductStatusUpdateRequestDto {
    @NotNull(message = "상품 상태는 필수 선택입니다.")
    private ProductStatus status;
}
