package com.example.ecommerce_backoffice.customer.dto;

import com.example.ecommerce_backoffice.customer.entity.Customer;
import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CustomerListResponseDto {

    private final Long id;

    private final String name;
    private final String email;
    private final String phone;

    private final CustomerStatus status;
    private final LocalDateTime createdAt;

    public static CustomerListResponseDto from(Customer customer) {
        return new CustomerListResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStatus(),
                customer.getCreatedAt()
        );
    }
}
