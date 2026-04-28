package com.example.ecommerce_backoffice.customer.service;

import com.example.ecommerce_backoffice.common.exception.CustomerNotFoundException;
import com.example.ecommerce_backoffice.customer.dto.*;
import com.example.ecommerce_backoffice.customer.entity.Customer;
import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;
import com.example.ecommerce_backoffice.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 다건 조회
    @Transactional(readOnly = true)
    public CustomerPageResponseDto getCustomerList(String keyword, CustomerStatus status, Pageable pageable) {

        Page<Customer> customerPage = customerRepository.findCustomer(keyword, status, pageable);

        Page<CustomerListResponseDto> dtoPage = customerPage.map(CustomerListResponseDto::from);
        return CustomerPageResponseDto.from(dtoPage);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public CustomerDetailResponseDto getCustomer(Long customerId) {
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerDetailResponseDto.from(customer);
    }

    // 정보 수정
    @Transactional
    public CustomerUpdateResponseDto updateCustomer(Long customerId, CustomerUpdateRequestDto requestDto) {
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        customer.update(requestDto.getName(), requestDto.getEmail(), requestDto.getPhone());
        return CustomerUpdateResponseDto.from(customer);
    }

    // 상태 변경
    @Transactional
    public void updateStatus(Long customerId, CustomerUpdateStatusRequestDto requestDto) {
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        customer.updateStatus(requestDto.getStatus());
    }

    // 삭제
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        customer.delete();
    }
}
