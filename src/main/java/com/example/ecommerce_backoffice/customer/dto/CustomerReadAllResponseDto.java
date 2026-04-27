package com.example.ecommerce_backoffice.customer.dto;

import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerReadAllResponseDto {

    private Long id;

    private String name;
    private String email;
    private String phone;

    private CustomerStatus status;
    private LocalDateTime createdAt;


    public CustomerReadAllResponseDto(Long id, String name, String email, String phone, CustomerStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createdAt = createdAt;
    }
}
