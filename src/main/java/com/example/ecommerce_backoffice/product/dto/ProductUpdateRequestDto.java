package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import lombok.Getter;

@Getter
public class ProductUpdateRequestDto {
    private String name;
    private ProductCategory category;
    private int price;
}

