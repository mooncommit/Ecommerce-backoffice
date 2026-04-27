package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import lombok.Getter;

import java.time.LocalDateTime;

// 관리자 승인 응답 DTO
@Getter
public class AdminApproveResponseDto {

    private final Long id;
    private final AdminStatus status;
    private final LocalDateTime approvedAt;
    private final LocalDateTime rejectedAt;
    private final String rejectionReason;

    public AdminApproveResponseDto(
            Long id,
            AdminStatus status,
            LocalDateTime approvedAt,
            LocalDateTime rejectedAt,
            String rejectionReason
    ) {
        this.id = id;
        this.status = status;
        this.approvedAt = approvedAt;
        this.rejectedAt = rejectedAt;
        this.rejectionReason = rejectionReason;
    }

    // 엔티티를 승인 응답 DTO로 변환
    public static AdminApproveResponseDto from(Admin admin) {
        return new AdminApproveResponseDto(
                admin.getId(),
                admin.getStatus(),
                admin.getApprovedAt(),
                admin.getRejectedAt(),
                admin.getRejectionReason()
        );
    }
}
