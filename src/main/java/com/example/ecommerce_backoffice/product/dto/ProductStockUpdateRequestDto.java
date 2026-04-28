package com.example.ecommerce_backoffice.product.dto;


import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class ProductStockUpdateRequestDto {

    @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    private int stock;
}
