package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 상품 정보 수정 응답 DTO
@Getter
@RequiredArgsConstructor
public class ProductUpdateResponseDto {
    private final Long id;
    private final String name;
    private final ProductCategory category;
    private final int price;
    private final int stock;
    private final ProductStatus status;
    private final LocalDateTime createdAt;

    public static ProductUpdateResponseDto from(Product product) {
        return new ProductUpdateResponseDto(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt()
        );
    }
}
