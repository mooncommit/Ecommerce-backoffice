package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 상품 단건 조회 응답 DTO (관리자 이메일 포함)
@Getter
@RequiredArgsConstructor
public class ProductDetailResponseDto {
    private final Long id;
    private final String name;
    private final ProductCategory category;
    private final int price;
    private final int stock;
    private final ProductStatus status;
    private final LocalDateTime createdAt;
    private final String adminName;
    private final String adminEmail;

    public static ProductDetailResponseDto from(Product product) {
        return new ProductDetailResponseDto(
        product.getId(),
        product.getName(),
        product.getCategory(),
        product.getPrice(),
        product.getStock(),
        product.getStatus(),
        product.getCreatedAt(),
        product.getAdmin().getName(),
        product.getAdmin().getEmail()
        );
    }
}
