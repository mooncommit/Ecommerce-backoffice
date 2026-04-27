package com.example.ecommerce_backoffice.product.controller;


import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.product.dto.*;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import com.example.ecommerce_backoffice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    // 상품 다건 조회 API (페이징)
    @GetMapping
    public ResponseEntity<Page<ProductListResponseDto>> getProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) ProductStatus status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {
        Page<ProductListResponseDto> responseDto = productService.getProducts(
                keyword, category, status, page, size, sortBy, sortOrder);

        return ResponseEntity.ok(responseDto);
    }

    // 상품 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponseDto> getProduct(@PathVariable Long id) {
        ProductDetailResponseDto responseDto = productService.getProduct(id);
        return ResponseEntity.ok(responseDto);
    }

    // 상품 정보 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<ProductUpdateResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequestDto requestDto) {
        ProductUpdateResponseDto responseDto = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 상품 재고 변경 API
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductStockResponseDto> updateStock(
            @PathVariable Long id,
            @RequestBody ProductStockRequestDto requestDto) {
        ProductStockResponseDto responseDto = productService.updateStock(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 상품 상태 변경 API
    @PatchMapping("/{id}/status")
    public ResponseEntity<ProductStatusResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody ProductStatusRequestDto requestDto) {
        ProductStatusResponseDto responseDto = productService.updateStatus(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
