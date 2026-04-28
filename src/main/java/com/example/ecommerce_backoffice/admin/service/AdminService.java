package com.example.ecommerce_backoffice.admin.service;

import com.example.ecommerce_backoffice.admin.dto.*;
import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.admin.repository.AdminRepository;
import com.example.ecommerce_backoffice.common.exception.AdminAlreadyProcessedException;
import com.example.ecommerce_backoffice.common.exception.AdminNotFoundException;
import com.example.ecommerce_backoffice.common.exception.PasswordMismatchException;
import com.example.ecommerce_backoffice.common.exception.UnauthorizedException;
import com.example.ecommerce_backoffice.config.PasswordEncoder;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

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

    // 관리자 다건 조회
    @Transactional(readOnly = true)
    public AdminPageResponseDto getAdmins(Pageable pageable, String keyword, AdminRole adminRole, AdminStatus adminStatus) {
        Page<Admin> adminList = adminRepository.findAdmins(keyword, adminRole, adminStatus, pageable);
        Page<AdminListResponseDto> dtoPage = adminList.map(AdminListResponseDto::from);
        return AdminPageResponseDto.from(dtoPage);
    }

    // 관리자 정보 수정
    @Transactional
    public AdminUpdateResponseDto updateAdmin(Long id, AdminUpdateRequestDto request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(AdminNotFoundException::new);

        admin.update(
                request.getName(),
                request.getEmail(),
                request.getPhone()
        );

        return AdminUpdateResponseDto.from(admin);
    }

    // 관리자 역할 수정
    @Transactional
    public AdminRoleUpdateResponseDto roleUpdateAdmin(Long id, AdminRoleUpdateRequestDto request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(AdminNotFoundException::new);

        admin.roleUpdate(request.getRole());

        return AdminRoleUpdateResponseDto.from(admin);

    }

    // 관리자 상태 수정
    @Transactional
    public AdminStatusUpdateResponseDto statusUpdateAdmin(Long id, AdminStatusUpdateRequestDto request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(AdminNotFoundException::new);

        admin.statusUpdate(request.getStatus());

        return AdminStatusUpdateResponseDto.from(admin);
    }

    // 관리자 삭제
    @Transactional
    public void deleteAdmin(Long adminId){
        Admin foundAdmin = adminRepository.findById(adminId)
                .orElseThrow(AdminNotFoundException::new);

        foundAdmin.changeStatus(AdminStatus.INACTIVE);
    }

    // 내 프로필 조회
    @Transactional(readOnly = true)
    public AdminGetProfileResponseDto getProfile(HttpSession session) {
        SessionAdmin sessionAdmin = (SessionAdmin) session.getAttribute("admin");

        if (sessionAdmin == null) {
            throw new UnauthorizedException();
        }

        Admin admin = adminRepository.findById(sessionAdmin.getId())
                .orElseThrow(AdminNotFoundException::new);

        return AdminGetProfileResponseDto.from(admin);
    }

    // 내 프로필 수정
    @Transactional
    public AdminPatchProfileResponseDto updateProfile(HttpSession session, AdminPatchProfileRequestDto request) {
        SessionAdmin sessionAdmin = (SessionAdmin) session.getAttribute("admin");

        if (sessionAdmin == null) {
            throw new UnauthorizedException();
        }

        Admin admin = adminRepository.findById(sessionAdmin.getId())
                .orElseThrow(AdminNotFoundException::new);

        admin.update(
                request.getName(),
                request.getEmail(),
                request.getPhone()
        );
        return AdminPatchProfileResponseDto.from(admin);
    }

    // 비밀번호 변경
    @Transactional
    public AdminPasswordUpdateResponseDto updateAdminPassword(
            HttpSession session,
            AdminPasswordUpdateRequestDto request
    ) {
        SessionAdmin sessionAdmin = (SessionAdmin) session.getAttribute("admin");

        if (sessionAdmin == null) {
            throw new UnauthorizedException();
        }

        Admin admin = adminRepository.findById(sessionAdmin.getId())
                .orElseThrow(AdminNotFoundException::new);

        if (!passwordEncoder.matches(request.getCurrentPassword(), admin.getPassword())) {
            throw new PasswordMismatchException();
        }

        admin.passwordUpdate(passwordEncoder.encode(request.getNewPassword()));

        return new AdminPasswordUpdateResponseDto("비밀번호가 변경되었습니다.");
    }

}
