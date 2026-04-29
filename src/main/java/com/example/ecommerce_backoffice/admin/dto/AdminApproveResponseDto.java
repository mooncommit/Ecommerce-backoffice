package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 관리자 승인 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminApproveResponseDto {

    private final Long id;
    private final AdminStatus status;
    private final LocalDateTime approvedAt;

    // 엔티티를 승인 응답 DTO로 변환
    public static AdminApproveResponseDto from(Admin admin) {
        return new AdminApproveResponseDto(
                admin.getId(),
                admin.getStatus(),
                admin.getApprovedAt()
        );
    }
}
