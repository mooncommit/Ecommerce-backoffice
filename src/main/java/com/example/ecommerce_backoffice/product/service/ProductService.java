package com.example.ecommerce_backoffice.product.service;


import com.example.ecommerce_backoffice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    // DB 접근용
    private final ProductRepository productRepository;
}
