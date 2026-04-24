package com.example.ecommerce_backoffice.product.repository;

import com.example.ecommerce_backoffice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
