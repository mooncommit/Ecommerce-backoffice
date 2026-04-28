package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;

@Getter
public class ProductStatusUpdateRequestDto {
    private ProductStatus status;
}
