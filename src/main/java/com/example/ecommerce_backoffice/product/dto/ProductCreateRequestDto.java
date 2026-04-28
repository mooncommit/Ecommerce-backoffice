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
    @NotBlank(message = "상품명은 필수 입력입니다.")
    private String name;

    @NotNull(message = "카테고리는 필수 입력입니다.")
    private ProductCategory category;

    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private int price;

    @Min(value = 0, message = "재고는 0 이상이어야 합니다.")
    private int stock;

    @NotNull(message = "상품 상태는 필수 입력입니다.")
    private ProductStatus status;

}
