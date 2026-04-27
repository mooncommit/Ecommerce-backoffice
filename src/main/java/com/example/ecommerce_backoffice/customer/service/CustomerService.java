package com.example.ecommerce_backoffice.customer.service;

import com.example.ecommerce_backoffice.common.exception.CustomerNotFoundException;
import com.example.ecommerce_backoffice.customer.dto.*;
import com.example.ecommerce_backoffice.customer.entity.Customer;
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
        return customerRepository.findAllByDeletedAtIsNull().stream()
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

    // 단건 조회
    @Transactional(readOnly = true)
    public CustomerReadResponseDto getCustomer(Long id) {
        Customer customer = customerRepository.findAllByDeletedAtIsNull(id)
                .orElseThrow(CustomerNotFoundException::new);
        return new CustomerReadResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStatus(),
                customer.getCreatedAt()
        );
    }

    // 정보 수정
    @Transactional
    public CustomerUpdateResponseDto updateCustomer(Long id, CustomerUpdateRequestDto requestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        customer.update(requestDto.getName(), requestDto.getEmail(), requestDto.getPhone());
        return new CustomerUpdateResponseDto(
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    // 상태 변경
    @Transactional
    public void updateStatus(Long id, CustomerUpdateStatusRequestDto requestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        customer.updateStatus(requestDto.getStatus());
    }

    // 삭제
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        customer.delete();
    }
}
