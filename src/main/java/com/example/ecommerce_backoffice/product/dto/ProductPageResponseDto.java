package com.example.ecommerce_backoffice.product.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProductPageResponseDto {

    private final int currentPage;
    private final int pageSize;
    private final Long totalCount;
    private final int totalPages;
    private final List<ProductListResponseDto> ProductList;

    public static ProductPageResponseDto from(Page<ProductListResponseDto> page) {
        return new ProductPageResponseDto(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }
}