package com.example.ecommerce_backoffice.product.dto;


import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// 상품 등록 요청 DTO
@Getter
public class ProductCreateRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private ProductCategory category;

    @Min(0)
    private int price;

    @Min(0)
    private int stock;

    @NotNull
    private ProductStatus status;

}
