package com.example.ecommerce_backoffice.customer.dto;

import lombok.Getter;

@Getter
public class CustomerUpdateResponseDto {

    private String name;
    private String email;
    private String phone;

    public CustomerUpdateResponseDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
