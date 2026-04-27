package com.example.ecommerce_backoffice.product.service;


import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.common.exception.ProductNotFoundException;
import com.example.ecommerce_backoffice.product.dto.*;
import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import com.example.ecommerce_backoffice.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    // DB 접근용
    private final ProductRepository productRepository;

    // 상품 등록 - 요청 데이터 받아서 DB에 저장 후 응답 반환
    @Transactional
    public ProductCreateResponseDto createProduct(Admin admin, ProductCreateRequestDto requestDto) {
        // 1. 요청 데이터로 Product 엔티티 생성하기
        Product product = new Product(
                admin,
                requestDto.getName(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getStock(),
                requestDto.getStatus()
        );

        // 2. DB에 저장하기
        Product savedProduct = productRepository.save(product);

        // 3. 저장딘 엔티티를 응답 DTO로 변환해서 반환하기
        return new ProductCreateResponseDto(savedProduct);
    }

    // 상품 다건 조회 (검색 + 필터 + 페이징 + 정렬)
    @Transactional
    public Page<ProductListResponseDto> getProducts(
            String keyword, ProductCategory category, ProductStatus status,
            int page, int size, String sortBy, String sortOrder) {

        // 정렬 설정
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);

        // 페이징 + 정렬 설정
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        // DB에서 검색 + 필터 + 페이징 조회
        Page<Product> products = productRepository.findProducts(keyword, category, status, pageable);

        // Product 엔티티를 응답 DTO로 변환 후 반환하기
        return products.map(ProductListResponseDto::new);

    }

    // 상품 단건 조회 - ID로 상품 하나 조회 하고 없으면 예외 발생
    @Transactional
    public ProductDetailResponseDto getProduct(Long id) {
        // ID로 상품 하나 조회 하고 없으면 ProductNotFoundException 던지기
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException());
        return new ProductDetailResponseDto(product);
    }

    // 상품 정보 수정 - 상품명, 카테고리, 가격만 변경
    @Transactional
    public ProductUpdateResponseDto updateProduct(Long id, ProductUpdateRequestDto requestDto) {
        // ID로 상품 조회 하고 없으면 예외 발생
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException());

        // 상품 정보 수정
        product.updateInfo(
                requestDto.getName(),
                requestDto.getCategory(),
                requestDto.getPrice()
        );

        // 수정된 상품 저장
        Product savedProduct = productRepository.save(product);

        // 수정된 상품을 응답 DTO로 변환해서 반환하기
        return new ProductUpdateResponseDto(savedProduct);
    }

    // 상품 재고 변경
    @Transactional
    public ProductStockResponseDto updateStock(Long id, ProductStockRequestDto requestDto) {
        // ID로 상품 조회 하고 없으면 예외 발생
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException());

        // 재고 변경
        product.updateStock(requestDto.getStock());

        // 변경된 상품 저장
        Product savedProduct = productRepository.save(product);

        // 응답 DTO로 변호나해서 반환
        return new ProductStockResponseDto(savedProduct);
    }
}
