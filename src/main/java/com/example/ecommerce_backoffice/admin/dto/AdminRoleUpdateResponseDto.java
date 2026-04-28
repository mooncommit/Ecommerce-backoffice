package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 관리자 역할 수정 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminRoleUpdateResponseDto {

    private final Long id;
    private final AdminRole role;

    // 엔티티를 관리자 역할 수정 응답 DTO로 변환
    public static AdminRoleUpdateResponseDto from(Admin admin) {
        return new AdminRoleUpdateResponseDto(
                admin.getId(),
                admin.getRole()
        );
    }
}
