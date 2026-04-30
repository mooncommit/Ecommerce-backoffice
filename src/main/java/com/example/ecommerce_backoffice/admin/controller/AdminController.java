package com.example.ecommerce_backoffice.admin.controller;

import com.example.ecommerce_backoffice.admin.dto.*;
import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.admin.service.AdminService;
import com.example.ecommerce_backoffice.common.dto.ApiResponseDto;
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
    public ResponseEntity<ApiResponseDto<AdminApproveResponseDto>> approveAdmin(@PathVariable Long adminId) {
        AdminApproveResponseDto data = adminService.approveAdmin(adminId);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 승인 성공", data));
    }

    // 관리자 거부 API
    @PostMapping("/{adminId}/reject")
    public ResponseEntity<ApiResponseDto<AdminRejectResponseDto>> rejectAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRejectRequestDto requestDto) {
        AdminRejectResponseDto data = adminService.rejectAdmin(adminId, requestDto);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 거부 성공", data));
    }

    // 관리자 단건 조회 API
    @GetMapping("/{adminId}")
    public ResponseEntity<ApiResponseDto<AdminDetailResponseDto>> getAdmin(@PathVariable Long adminId) {
        AdminDetailResponseDto data = adminService.getAdmin(adminId);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 단건 조회 성공", data));
    }

    // 관리자 다건 조회 API
    @GetMapping
    public ResponseEntity<ApiResponseDto<AdminPageResponseDto>> getAdmins(
            @PageableDefault(page = 0, size = 10, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) AdminRole adminRole,
            @RequestParam(required = false) AdminStatus adminStatus) {
        AdminPageResponseDto data = adminService.getAdmins(pageable, keyword, adminRole, adminStatus);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 목록 조회 성공", data));
    }

    // 관리자 정보 수정 API
    @PatchMapping("/{adminId}")
    public ResponseEntity<ApiResponseDto<AdminUpdateResponseDto>> updateAdmin(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminUpdateRequestDto requestDto) {
        AdminUpdateResponseDto data = adminService.updateAdmin(adminId, requestDto);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 정보 수정 성공", data));
    }

    // 관리자 역할 수정 API
    @PatchMapping("/{adminId}/role")
    public ResponseEntity<ApiResponseDto<AdminRoleUpdateResponseDto>> updateAdminRole(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminRoleUpdateRequestDto requestDto) {
        AdminRoleUpdateResponseDto data = adminService.updateAdminRole(adminId, requestDto);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 역할 수정 성공", data));
    }

    // 관리자 상태 수정 API
    @PatchMapping("/{adminId}/status")
    public ResponseEntity<ApiResponseDto<AdminStatusUpdateResponseDto>> updateAdminStatus(
            @PathVariable Long adminId,
            @Valid @RequestBody AdminStatusUpdateRequestDto requestDto) {
        AdminStatusUpdateResponseDto data = adminService.updateAdminStatus(adminId, requestDto);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자 상태 수정 성공", data));
    }


    // 관리자 삭제 API
    @DeleteMapping("/{adminId}")
    public ResponseEntity<ApiResponseDto<Void>> deleteAdmin(
            @PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "관리자가 삭제되었습니다.", null));
    }

    // 내(로그인한 유저) 프로필 조회 API
    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<AdminProfileGetResponseDto>> getProfile(HttpSession session) {
        AdminProfileGetResponseDto data = adminService.getProfile(session);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "내 프로필 조회 성공", data));
    }

    // 내(로그인한 유저) 프로필 수정 API
    @PatchMapping("/me")
    public ResponseEntity<ApiResponseDto<AdminProfilePatchResponseDto>> patchProfile(
            HttpSession session,
            @Valid @RequestBody AdminProfilePatchRequestDto requestDto) {
        AdminProfilePatchResponseDto data = adminService.updateProfile(session, requestDto);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "내 프로필 수정 성공", data));
    }

    // 비밀번호 변경 API
    @PatchMapping("/me/password")
    public ResponseEntity<ApiResponseDto<AdminPasswordUpdateResponseDto>> passwordUpdateAdmin(
            HttpSession session,
            @Valid @RequestBody AdminPasswordUpdateRequestDto requestDto) {
        AdminPasswordUpdateResponseDto data = adminService.updateAdminPassword(session, requestDto);
        return ResponseEntity.ok(ApiResponseDto.success(HttpStatus.OK, "비밀번호 변경 성공", data));
    }
}
