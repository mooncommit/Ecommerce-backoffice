package com.example.ecommerce_backoffice.product.controller;


import com.example.ecommerce_backoffice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    // 비즈니스 로직 처리
    private final ProductService productService;
}
