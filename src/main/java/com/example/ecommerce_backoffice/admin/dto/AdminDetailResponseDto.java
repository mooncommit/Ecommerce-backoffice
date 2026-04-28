package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

// 관리자 단건 조회 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminDetailResponseDto {

    private final Long id;
    private final String name;
    private final String email;
    private final String phone;
    private final AdminRole role;
    private final AdminStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime approvedAt;


    // 엔티티를 단건 조회 응답 DTO로 변환
    public static AdminDetailResponseDto from(Admin admin) {
        return new AdminDetailResponseDto(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPhone(),
                admin.getRole(),
                admin.getStatus(),
                admin.getCreatedAt(),
                admin.getApprovedAt()
        );
    }
}
