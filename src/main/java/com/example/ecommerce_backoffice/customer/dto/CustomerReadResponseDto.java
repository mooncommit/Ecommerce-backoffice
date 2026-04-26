package com.example.ecommerce_backoffice.customer.dto;

import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;

import java.time.LocalDateTime;

public class CustomerReadResponseDto {

    private Long id;

    private String name;
    private String email;
    private String phone;

    private CustomerStatus status;
    private LocalDateTime createdAt;


    public CustomerReadResponseDto(Long id, String name, String email, String phone, CustomerStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public CustomerStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
