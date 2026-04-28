package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminGetProfileResponseDto {

    private final String name;
    private final String email;
    private final String phone;

    public static AdminGetProfileResponseDto from(Admin admin) {
        return new AdminGetProfileResponseDto(
                admin.getName(),
                admin.getEmail(),
                admin.getPhone()
        );
    }
}
