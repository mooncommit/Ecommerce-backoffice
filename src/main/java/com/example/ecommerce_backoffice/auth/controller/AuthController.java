package com.example.ecommerce_backoffice.auth.controller;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.auth.dto.RegisterCreateRequestDto;
import com.example.ecommerce_backoffice.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/signup")
public class AuthController {
    //속성
    private AuthService authService;
    //생성자
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    //기능
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody RegisterCreateRequestDto request) {
        Admin admin= authService.createRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("관리자등록이 완료되었습니다. 슈퍼 관리자의 승인이 있어야 로그인 가능합니다.");
    }
}
