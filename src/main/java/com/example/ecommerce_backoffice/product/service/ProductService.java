package com.example.ecommerce_backoffice.product.service;


import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.product.dto.ProductCreateRequestDto;
import com.example.ecommerce_backoffice.product.dto.ProductCreateResponseDto;
import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

        // 3. 저장딘 엔티티를 응답 DTO로 변환해서 반환
        return new ProductCreateResponseDto(savedProduct);
    }

}
