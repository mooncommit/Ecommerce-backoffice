package com.example.ecommerce_backoffice.product.dto;


import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class ProductStockUpdateRequestDto {

    @Min(0)
    private int stock;
}
