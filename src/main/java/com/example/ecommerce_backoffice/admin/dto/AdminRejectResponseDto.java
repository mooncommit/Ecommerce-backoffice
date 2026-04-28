package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 관리자 거부 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminRejectResponseDto {

    private final Long id;
    private final AdminStatus status;
    private final LocalDateTime approvedAt;
    private final LocalDateTime rejectedAt;
    private final String rejectionReason;

    // 엔티티를 거부 응답 DTO로 변환
    public static AdminRejectResponseDto from(Admin admin) {
        return new AdminRejectResponseDto(
                admin.getId(),
                admin.getStatus(),
                admin.getApprovedAt(),
                admin.getRejectedAt(),
                admin.getRejectionReason()
        );
    }
}
