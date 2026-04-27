package com.example.ecommerce_backoffice.product.repository;

import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // 검색 + 필터 + 페이징 조회
    @Query("SELECT p FROM Product p WHERE " +
            "(:keyword IS NULL OR p.name LIKE %:keyword%) AND " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:status IS NULL OR p.status = :status)")
    Page<Product> findProducts(
            @Param("keyword") String keyword,
            @Param("category")ProductCategory category,
            @Param("status")ProductStatus status,
            Pageable pageable);

}
