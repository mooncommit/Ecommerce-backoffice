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
    @PostMapping("/{id}/approve")
    public ResponseEntity<AdminApproveResponseDto> approveAdmin(@PathVariable("id") Long adminId) {
        return ResponseEntity.ok(adminService.approveAdmin(adminId));
    }

    // 관리자 거부 API
    @PostMapping("/{id}/reject")
    public ResponseEntity<AdminRejectResponseDto> rejectAdmin(
            @PathVariable("id") Long adminId,
            @Valid @RequestBody AdminRejectRequestDto requestDto) {
        return ResponseEntity.ok(adminService.rejectAdmin(adminId, requestDto));
    }

    // 관리자 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<AdminDetailResponseDto> getAdmin(@PathVariable("id") Long adminId) {
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
    @PatchMapping("/{id}")
    public ResponseEntity<AdminUpdateResponseDto> updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminUpdateRequestDto request) {
        return ResponseEntity.ok(adminService.updateAdmin(id, request));
    }

    // 관리자 역할 수정 API
    @PatchMapping("/{id}/role")
    public ResponseEntity<AdminRoleUpdateResponseDto> roleUpdateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminRoleUpdateRequestDto request) {
        return ResponseEntity.ok(adminService.roleUpdateAdmin(id, request));
    }

    // 관리자 상태 수정 API
    @PatchMapping("/{id}/status")
    public ResponseEntity<AdminStatusUpdateResponseDto> statusUpdateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminStatusUpdateRequestDto request) {
        return ResponseEntity.ok(adminService.statusUpdateAdmin(id, request));
    }


    // 관리자 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmins(
            @PathVariable Long id){
            adminService.deleteAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 내(로그인한 유저) 프로필 조회 API
    @GetMapping("/me")
    public ResponseEntity<AdminProfileGetResponseDto> getProfile(HttpSession httpSession) {
        return ResponseEntity.ok(adminService.getProfile(httpSession));
    }
    // 내(로그인한 유저) 프로필 수정 API
    @PatchMapping("/me")
    public ResponseEntity<AdminProfilePatchResponseDto> patchProfile(
            HttpSession httpSession,
            @Valid @RequestBody AdminProfileRequestPatchDto request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateProfile(httpSession, request));
    }

    // 비밀번호 변경 API
    @PatchMapping("/me/password")
    public ResponseEntity<AdminPasswordUpdateResponseDto> passwordUpdateAdmin(
            HttpSession httpSession,
            @Valid @RequestBody AdminPasswordUpdateRequestDto request) {
        return ResponseEntity.ok(adminService.updateAdminPassword(httpSession, request));
    }
}






