package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;

import java.time.LocalDateTime;

// 상품 정보 수정 응답 DTO
@Getter
public class ProductUpdateResponseDto {
    private Long id;
    private String name;
    private ProductCategory category;
    private int price;
    private int stock;
    private ProductStatus status;
    private LocalDateTime createdAt;

    // Product 엔티티를 응답 DTO로 변환하는 생성자
    public ProductUpdateResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.status = product.getStatus();
        this.createdAt = product.getCreatedAt();
    }
}
