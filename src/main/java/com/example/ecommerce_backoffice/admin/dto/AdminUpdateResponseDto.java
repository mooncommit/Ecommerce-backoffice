package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 관리자 정보 수정 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminUpdateResponseDto {

    private final String name;
    private final String email;
    private final String phone;

    // 엔티티를 관리자 정보 수정 응답 DTO로 변환
    public static AdminUpdateResponseDto from(Admin admin) {
        return new AdminUpdateResponseDto(
                admin.getName(),
                admin.getEmail(),
                admin.getPhone()
        );
    }
}
