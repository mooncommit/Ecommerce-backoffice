package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductListResponseDto {
    private Long id;
    private String name;
    private ProductCategory category;
    private int price;
    private int stock;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private String adminName;

    // Product 엔티티를 응답 DTO로 변환하는 생성자
    public ProductListResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.status = product.getStatus();
        this.createdAt = product.getCreatedAt();
        this.adminName = product.getAdmin().getName();
    }
}
