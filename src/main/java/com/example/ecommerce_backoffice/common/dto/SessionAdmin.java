package com.example.ecommerce_backoffice.common.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import lombok.Getter;

@Getter
public class SessionAdmin {
    private final Long id;
    private final String email;
    private final AdminRole role;

    public SessionAdmin(Long id, String email, AdminRole role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
