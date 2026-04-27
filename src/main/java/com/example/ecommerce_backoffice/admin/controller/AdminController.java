package com.example.ecommerce_backoffice.admin.controller;

import com.example.ecommerce_backoffice.admin.dto.AdminApproveResponseDto;
import com.example.ecommerce_backoffice.admin.dto.AdminDetailResponseDto;
import com.example.ecommerce_backoffice.admin.dto.AdminRejectRequestDto;
import com.example.ecommerce_backoffice.admin.dto.AdminRejectResponseDto;
import com.example.ecommerce_backoffice.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
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
            @Valid @RequestBody AdminRejectRequestDto requestDto
    ) {
        return ResponseEntity.ok(adminService.rejectAdmin(adminId, requestDto));
    }

    // 관리자 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<AdminDetailResponseDto> getAdmin(@PathVariable("id") Long adminId) {
        return ResponseEntity.ok(adminService.getAdmin(adminId));
    }

}
