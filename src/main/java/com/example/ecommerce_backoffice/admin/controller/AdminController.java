package com.example.ecommerce_backoffice.admin.controller;

import com.example.ecommerce_backoffice.admin.dto.*;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.admin.service.AdminService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 관리자 승인 API
    @PostMapping("/{adminId}/approve")
    public ResponseEntity<AdminApproveResponseDto> approveAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(adminService.approveAdmin(adminId));
    }

    // 관리자 거부 API
    @PostMapping("/{adminId}/reject")
    public ResponseEntity<AdminRejectResponseDto> rejectAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRejectRequestDto requestDto) {
        return ResponseEntity.ok(adminService.rejectAdmin(adminId, requestDto));
    }

    // 관리자 단건 조회 API
    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDetailResponseDto> getAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(adminService.getAdmin(adminId));
    }

    // 관리자 다건 조회 API
    @GetMapping
    public ResponseEntity<AdminPageResponseDto> getAdmins(
            @PageableDefault(page = 0, size = 10, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) AdminRole adminRole,
            @RequestParam(required = false) AdminStatus adminStatus) {
        return ResponseEntity.ok(adminService.getAdmins(pageable, keyword, adminRole, adminStatus));
    }

    // 관리자 정보 수정 API
    @PatchMapping("/{adminId}")
    public ResponseEntity<AdminUpdateResponseDto> updateAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateRequestDto requestDto) {
        return ResponseEntity.ok(adminService.updateAdmin(adminId, requestDto));
    }

    // 관리자 역할 수정 API
    @PatchMapping("/{adminId}/role")
    public ResponseEntity<AdminRoleUpdateResponseDto> updateAdminRole(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRoleUpdateRequestDto requestDto) {
        return ResponseEntity.ok(adminService.updateAdminRole(adminId, requestDto));
    }

    // 관리자 상태 수정 API
    @PatchMapping("/{adminId}/status")
    public ResponseEntity<AdminStatusUpdateResponseDto> updateAdminStatus(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminStatusUpdateRequestDto requestDto) {
        return ResponseEntity.ok(adminService.updateAdminStatus(adminId, requestDto));
    }


    // 관리자 삭제 API
    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(
            @PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 내(로그인한 유저) 프로필 조회 API
    @GetMapping("/me")
    public ResponseEntity<AdminProfileGetResponseDto> getProfile(HttpSession session) {
        return ResponseEntity.ok(adminService.getProfile(session));
    }

    // 내(로그인한 유저) 프로필 수정 API
    @PatchMapping("/me")
    public ResponseEntity<AdminProfilePatchResponseDto> patchProfile(
            HttpSession session,
            @Valid @RequestBody AdminProfilePatchRequestDto requestDto) {
        return ResponseEntity.ok(adminService.updateProfile(session, requestDto));
    }

    // 비밀번호 변경 API
    @PatchMapping("/me/password")
    public ResponseEntity<AdminPasswordUpdateResponseDto> passwordUpdateAdmin(
            HttpSession session,
            @Valid @RequestBody AdminPasswordUpdateRequestDto requestDto) {
        return ResponseEntity.ok(adminService.updateAdminPassword(session, requestDto));
    }
}
