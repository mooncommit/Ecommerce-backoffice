package com.example.ecommerce_backoffice.product.controller;


import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.product.dto.ProductCreateRequestDto;
import com.example.ecommerce_backoffice.product.dto.ProductCreateResponseDto;
import com.example.ecommerce_backoffice.product.dto.ProductDetailResponseDto;
import com.example.ecommerce_backoffice.product.dto.ProductListResponseDto;
import com.example.ecommerce_backoffice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    // 비즈니스 로직 처리
    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(@RequestBody ProductCreateRequestDto requestDto) {
        Admin admin = null;
        ProductCreateResponseDto responseDto = productService.createProduct(admin, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 상품 다건 조회 API
    @GetMapping
    public ResponseEntity<List<ProductListResponseDto>> getProducts() {
        List<ProductListResponseDto> responseDto = productService.getProducts();
        return ResponseEntity.ok(responseDto);
    }

    // 상품 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponseDto> getProduct(@PathVariable Long id) {
        ProductDetailResponseDto responseDto = productService.getProduct(id);
        return ResponseEntity.ok(responseDto);
    }
}
