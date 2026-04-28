package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 세션 저장용 관리자 DTO
@Getter
@RequiredArgsConstructor
public class SessionAdmin {

    private final Long id;
    private final String email;
    private final AdminRole role;
}
