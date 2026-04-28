package com.example.ecommerce_backoffice.customer.controller;

import com.example.ecommerce_backoffice.customer.dto.*;
import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;
import com.example.ecommerce_backoffice.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;


    // 다건 조회
    @GetMapping
    public ResponseEntity<CustomerPageResponseDto> getAllCustomers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) CustomerStatus status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return ResponseEntity.ok(customerService.getCustomerList(keyword, status, pageable));
    }

    // 단건 조회
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetailResponseDto> getCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    // 정보 수정
    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerUpdateResponseDto> updateCustomer(@PathVariable Long customerId, @Valid @RequestBody CustomerUpdateRequestDto requestDto) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, requestDto));
    }

    // 상태 변경
    @PatchMapping("/{customerId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long customerId, @Valid @RequestBody CustomerUpdateStatusRequestDto requestDto) {
        customerService.updateStatus(customerId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 삭제
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
