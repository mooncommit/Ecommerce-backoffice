package com.example.ecommerce_backoffice.auth.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;

public class SessionAdmin {
    private final Long id;
    private final String email;
    private final AdminRole role;

    public SessionAdmin(Admin admin) {
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.role = admin.getRole();
    }
}
