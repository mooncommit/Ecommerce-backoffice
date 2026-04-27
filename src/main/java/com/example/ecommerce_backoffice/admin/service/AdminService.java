package com.example.ecommerce_backoffice.admin.service;

import com.example.ecommerce_backoffice.admin.dto.AdminApproveResponseDto;
import com.example.ecommerce_backoffice.admin.dto.AdminDetailResponseDto;
import com.example.ecommerce_backoffice.admin.dto.AdminRejectRequestDto;
import com.example.ecommerce_backoffice.admin.dto.AdminRejectResponseDto;
import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.admin.repository.AdminRepository;
import com.example.ecommerce_backoffice.common.exception.AdminAlreadyProcessedException;
import com.example.ecommerce_backoffice.common.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    // 승인 대기 상태 관리자 승인 처리
    public AdminApproveResponseDto approveAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(AdminNotFoundException::new);

        validatePending(admin);
        admin.approve(LocalDateTime.now());

        return AdminApproveResponseDto.from(admin);
    }

    @Transactional
    // 거부 사유 포함 관리자 거부 처리
    public AdminRejectResponseDto rejectAdmin(Long adminId, AdminRejectRequestDto requestDto) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(AdminNotFoundException::new);

        validatePending(admin);
        admin.reject(LocalDateTime.now(), requestDto.getRejectionReason());

        return AdminRejectResponseDto.from(admin);
    }

    // 승인 대기 상태 검증
    private void validatePending(Admin admin) {
        if (admin.getStatus() != AdminStatus.PENDING) {
            throw new AdminAlreadyProcessedException();
        }
    }

    // 관리자 단건 조회
    @Transactional(readOnly = true)
    public AdminDetailResponseDto getAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(AdminNotFoundException::new);

        return AdminDetailResponseDto.from(admin);
    }

}
