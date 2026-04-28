package com.example.ecommerce_backoffice.product.dto;

import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 상품 등록 응답 DTO
@Getter
@RequiredArgsConstructor
public class ProductCreateResponseDto {
    private final Long id;
    private final String name;
    private final ProductCategory category;
    private final int price;
    private final int stock;
    private final ProductStatus status;
    private final LocalDateTime createdAt;

    public static ProductCreateResponseDto from(Product product) {
        return new ProductCreateResponseDto(
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
