package com.example.ecommerce_backoffice.product.controller;


import com.example.ecommerce_backoffice.common.dto.ApiResponseDto;
import com.example.ecommerce_backoffice.product.dto.*;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import com.example.ecommerce_backoffice.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<ApiResponseDto<ProductCreateResponseDto>> createProduct(@Valid @RequestBody ProductCreateRequestDto requestDto, HttpSession session) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDto.success(HttpStatus.CREATED, "상품이 등록되었습니다.", productService.createProduct(requestDto, session)));
    }

    // 상품 다건 조회 API (페이징)
    @GetMapping
    public ResponseEntity<ApiResponseDto<ProductPageResponseDto>> getProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) ProductStatus status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "상품 목록 조회 성공", productService.getProducts(keyword, category, status, pageable)));
    }

    // 상품 단건 조회 API
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<ProductDetailResponseDto>> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "상품 조회 성공", productService.getProduct(productId)));
    }

    // 상품 정보 수정 API
    @PatchMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<ProductUpdateResponseDto>> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductUpdateRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "상품 정보가 수정되었습니다.", productService.updateProduct(productId, requestDto)));
    }

    // 상품 재고 변경 API
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<ApiResponseDto<ProductStockUpdateResponseDto>> updateStock(@PathVariable Long productId, @Valid @RequestBody ProductStockUpdateRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "상품 재고가 변경되었습니다.", productService.updateStock(productId, requestDto)));
    }

    // 상품 상태 변경 API
    @PatchMapping("/{productId}/status")
    public ResponseEntity<ApiResponseDto<ProductStatusUpdateResponseDto>> updateStatus(@PathVariable Long productId, @Valid @RequestBody ProductStatusUpdateRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "상품 상태가 변경되었습니다.", productService.updateStatus(productId, requestDto)));
    }

    // 상품 삭제 API
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<Void>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponseDto.success(HttpStatus.NO_CONTENT, "상품 삭제 성공", null));
    }
}
