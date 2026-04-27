package com.example.ecommerce_backoffice.product.entity;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.common.entity.BaseEntity;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    // 상품명
    @Column(nullable = false, length = 255)
    private String name;

    // 카테고리 (전자기기, 패션/의류, 식품)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    // 가격
    @Column(nullable = false)
    private int price;

    // 재고
    @Column(nullable = false)
    private int stock;

    // 상품 상태 (ON_SALE, SOLD_OUT, DISCONTINUED)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    // 상품 생성자
    public Product(Admin admin, String name, ProductCategory category, int price, int stock, ProductStatus status) {
        this.admin = admin;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    // 상품 정보 수정 - 상품명, 카테고리, 가격만 변경
    public void updateInfo(String name, ProductCategory category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // 상품 재고 변경
    public void updateStock(int stock) {
        this.stock = stock;

        // 단종 상태면 상태 유지
        if (this.status == ProductStatus.DISCONTINUED) {
            return;
        }

        // 재고 0 이하 -> 품절 / 재고 1 이상 -> 판매중 재고에 따라 상태 자동으로 전환
        if (this.stock <= 0) {
            this.status = ProductStatus.SOLD_OUT;
        } else {
            this.status = ProductStatus.ON_SALE;
        }
    }

    // 상품 상태 변경
    public void updateStatus(ProductStatus status) {
        this.status = status;
    }
}


