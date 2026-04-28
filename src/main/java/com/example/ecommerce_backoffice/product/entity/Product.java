package com.example.ecommerce_backoffice.product.entity;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.common.entity.BaseEntity;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


    @Column(nullable = false, length = 255)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;


    @Column(nullable = false)
    private int price;


    @Column(nullable = false)
    private int stock;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    private LocalDateTime deletedAt;


    public Product(Admin admin, String name, ProductCategory category, int price, int stock, ProductStatus status) {
        this.admin = admin;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }


    public void updateInfo(String name, ProductCategory category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public void updateStock(int stock) {
        this.stock = stock;


        if (this.status == ProductStatus.DISCONTINUED) {
            return;
        }


        if (this.stock <= 0) {
            this.status = ProductStatus.SOLD_OUT;
        } else {
            this.status = ProductStatus.ON_SALE;
        }
    }


    public void updateStatus(ProductStatus status) {
        this.status = status;
    }


    public void decreaseStock(int quantity) {
        this.stock -= quantity;
        if (this.stock <= 0) {
            this.status = ProductStatus.SOLD_OUT;
        }
    }

    public void restoreStock(int quantity) {
        this.stock += quantity;
        if (this.status != ProductStatus.DISCONTINUED) {
            this.status = ProductStatus.ON_SALE;
        }
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}


