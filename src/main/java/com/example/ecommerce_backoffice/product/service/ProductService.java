package com.example.ecommerce_backoffice.product.service;


import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.repository.AdminRepository;
import com.example.ecommerce_backoffice.common.dto.SessionAdmin;
import com.example.ecommerce_backoffice.common.exception.AdminNotFoundException;
import com.example.ecommerce_backoffice.common.exception.ProductNotFoundException;
import com.example.ecommerce_backoffice.product.dto.*;
import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductCategory;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import com.example.ecommerce_backoffice.product.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    // DB 접근용
    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;

    // 상품 등록 - 요청 데이터 받아서 DB에 저장 후 응답 반환
    @Transactional
    public ProductCreateResponseDto createProduct(ProductCreateRequestDto requestDto, HttpSession session) {
        SessionAdmin sessionAdmin = (SessionAdmin) session.getAttribute("loginAdmin");
        Admin admin = adminRepository.findById(sessionAdmin.getId()).orElseThrow(AdminNotFoundException::new);

        Product product = new Product(
                admin,
                requestDto.getName(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getStock(),
                requestDto.getStatus()
        );

        // 저장딘 엔티티를 응답 DTO로 변환해서 반환하기
        return ProductCreateResponseDto.from(productRepository.save(product));
    }

    // 상품 다건 조회 (검색 + 필터 + 페이징 + 정렬)
    @Transactional(readOnly = true)
    public ProductPageResponseDto getProducts(
            String keyword, ProductCategory category, ProductStatus status, Pageable pageable) {

        // DB에서 검색 + 필터 + 페이징 조회
        Page<Product> productPage = productRepository.findProduct(keyword, category, status, pageable);
        Page<ProductListResponseDto> dtoPage = productPage.map(ProductListResponseDto::from);

        // Product 엔티티를 응답 DTO로 변환 후 반환하기
        return ProductPageResponseDto.from(dtoPage);

    }

    // 상품 단건 조회 - ID로 상품 하나 조회 하고 없으면 예외 발생
    @Transactional(readOnly = true)
    public ProductDetailResponseDto getProduct(Long productId) {

        // ID로 상품 하나 조회 하고 없으면 ProductNotFoundException 던지기
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        return ProductDetailResponseDto.from(product);
    }

    // 상품 정보 수정 - 상품명, 카테고리, 가격만 변경
    @Transactional
    public ProductUpdateResponseDto updateProduct(Long productId, ProductUpdateRequestDto requestDto) {
        // ID로 상품 조회 하고 없으면 예외 발생
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        // 상품 정보 수정
        product.updateInfo(
                requestDto.getName(),
                requestDto.getCategory(),
                requestDto.getPrice()
        );
        // 수정된 상품을 응답 DTO로 변환해서 반환하기
        return ProductUpdateResponseDto.from(product);
    }

    // 상품 재고 변경
    @Transactional
    public ProductStockUpdateResponseDto updateStock(Long productId, ProductStockUpdateRequestDto requestDto) {
        // ID로 상품 조회 하고 없으면 예외 발생
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        // 재고 변경
        product.updateStock(requestDto.getStock());

        // 응답 DTO로 변호나해서 반환
        return ProductStockUpdateResponseDto.from(product);
    }

    // 상품 상태 변경
    @Transactional
    public ProductStatusUpdateResponseDto updateStatus(Long productId, ProductStatusUpdateRequestDto requestDto) {
        // ID로 상품 조회 하고 없으면 예외 발생
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        // 상태 변경
        product.updateStatus(requestDto.getStatus());

        // 응답 DTO로 변환 후 반환
        return ProductStatusUpdateResponseDto.from(product);
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId) {
        // ID로 상품 조회 하고 없으면 예외 발생
        Product product = productRepository.findByIdAndDeletedAtIsNull(productId)
                .orElseThrow(ProductNotFoundException::new);

        product.delete();
    }
}
