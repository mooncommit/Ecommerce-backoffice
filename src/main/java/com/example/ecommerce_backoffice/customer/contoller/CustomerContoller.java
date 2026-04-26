package com.example.ecommerce_backoffice.customer.contoller;

import com.example.ecommerce_backoffice.customer.dto.CustomerReadAllResponseDto;
import com.example.ecommerce_backoffice.customer.service.CustomerService;
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
}
