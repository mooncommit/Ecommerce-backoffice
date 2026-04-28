package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 상품 재고 변경 응답 DTO
@RequiredArgsConstructor
@Getter
public class ProductStockResponseDto {
    private final Long id;
    private final String name;
    private final ProductCategory category;
    private final int price;
    private final int stock;
    private final ProductStatus status;
    private final LocalDateTime createdAt;

    // Product 엔티티를 응답 DTO로 변환하는 생성자
    public static ProductStockResponseDto from(Product product) {
        return new ProductStockResponseDto(
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
