package com.example.ecommerce_backoffice.customer.contoller;

import com.example.ecommerce_backoffice.customer.dto.CustomerReadAllResponseDto;
import com.example.ecommerce_backoffice.customer.dto.CustomerReadResponseDto;
import com.example.ecommerce_backoffice.customer.dto.CustomerUpdateRequestDto;
import com.example.ecommerce_backoffice.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerContoller {

    // 속성
    private final CustomerService customerService;


    // 다건 조회
    @GetMapping
    public ResponseEntity<List<CustomerReadAllResponseDto>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }

    // 단건 조회
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerReadResponseDto> getCustomer(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(customerId));
    }

    // 정보 수정
    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerReadResponseDto> updateCustomer(@PathVariable Long customerId,@Valid @RequestBody CustomerUpdateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customerId, requestDto));
    }
}
