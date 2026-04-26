package com.example.ecommerce_backoffice.customer.service;

import com.example.ecommerce_backoffice.customer.dto.CustomerReadAllResponseDto;
import com.example.ecommerce_backoffice.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 다건 조회
    @Transactional(readOnly = true)
    public List<CustomerReadAllResponseDto> getCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerReadAllResponseDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone(),
                        customer.getStatus(),
                        customer.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
