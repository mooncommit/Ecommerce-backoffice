package com.example.ecommerce_backoffice.admin.entity;

import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 관리자 테이블
 */
@Getter
@Entity
@Table(name = "admins")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminStatus status;

    // 관리자 승인 시각
    private LocalDateTime approvedAt;
    // 관리자 거부 시간
    private LocalDateTime rejectedAt;
    // 관리자 거부 사유
    private String rejectionReason;

    public Admin(String name, String email, String password, String phone, AdminRole role, AdminStatus status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    // 승인 대기 관리자 활성 처리
    public void approve(LocalDateTime approvedAt) {
        this.status = AdminStatus.ACTIVE;
        this.approvedAt = approvedAt;
        this.rejectedAt = null;
        this.rejectionReason = null;
    }

    // 승인 대기 관리자 거부 처리
    public void reject(LocalDateTime rejectedAt, String rejectionReason) {
        this.status = AdminStatus.REJECTED;
        this.rejectedAt = rejectedAt;
        this.rejectionReason = rejectionReason;
    }
}
