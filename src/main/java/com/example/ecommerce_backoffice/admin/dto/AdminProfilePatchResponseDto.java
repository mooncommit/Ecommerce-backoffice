package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminProfilePatchResponseDto {

    private final String name;
    private final String email;
    private final String phone;

    // 엔티티를 관리자 정보 수정 응답 DTO로 변환
    public static AdminProfilePatchResponseDto from(Admin admin) {
        return new AdminProfilePatchResponseDto(
                admin.getName(),
                admin.getEmail(),
                admin.getPhone()
        );
    }
}
