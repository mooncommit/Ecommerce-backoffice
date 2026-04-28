package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 관리자 상태 수정 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminStatusUpdateResponseDto {

    private final Long id;
    private final AdminStatus status;

    // 엔티티를 관리자 상태 수정 응답 DTO로 변환
    public static AdminStatusUpdateResponseDto from(Admin admin) {
        return new AdminStatusUpdateResponseDto(
                admin.getId(),
                admin.getStatus()
        );
    }
}
