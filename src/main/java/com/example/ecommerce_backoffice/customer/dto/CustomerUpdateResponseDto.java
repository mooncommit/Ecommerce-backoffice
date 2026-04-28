package com.example.ecommerce_backoffice.customer.dto;

import com.example.ecommerce_backoffice.customer.entity.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerUpdateResponseDto {

    private final String name;
    private final String email;
    private final String phone;

    public static CustomerUpdateResponseDto from(Customer customer) {
        return new CustomerUpdateResponseDto(
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone()
        );
    }
}
