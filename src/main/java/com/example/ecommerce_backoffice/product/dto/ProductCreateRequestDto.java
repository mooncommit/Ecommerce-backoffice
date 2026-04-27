package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;

// 상품 등록 요청 DTO
@Getter
public class ProductCreateRequestDto {
    private String name;
    private ProductCategory category;
    private Integer price;
    private Integer stock;
    private ProductStatus status;

}
