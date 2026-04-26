package com.example.ecommerce_backoffice.customer.dto;

import com.example.ecommerce_backoffice.customer.enums.CustomerStatus;
import jakarta.validation.constraints.NotNull;

public class CustomerUpdateStatusRequestDto {
    @NotNull(message = "상태를 입력해주세요.")
    private CustomerStatus status;

    public CustomerStatus getStatus() {
        return status;
    }
}
