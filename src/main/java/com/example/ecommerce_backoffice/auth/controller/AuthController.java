package com.example.ecommerce_backoffice.auth.controller;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.auth.dto.LoginRequestDto;
import com.example.ecommerce_backoffice.auth.dto.SignupCreateRequestDto;
import com.example.ecommerce_backoffice.common.dto.SessionAdmin;
import com.example.ecommerce_backoffice.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AuthController {

    private final AuthService authService;



    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid @RequestBody SignupCreateRequestDto request) {
        authService.createRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("관리자등록이 완료되었습니다. 슈퍼 관리자의 승인이 있어야 로그인 가능합니다.");
    }


    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@Valid @RequestBody LoginRequestDto request, HttpSession session ) {
        Admin admin = authService.loginAdmin(request);
        SessionAdmin sessionAdmin = new SessionAdmin(
                admin.getId(),
                admin.getEmail(),
                admin.getRole()
        );
        session.setAttribute("loginAdmin", sessionAdmin);

        return ResponseEntity.ok("로그인 성공!");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> adminLogout(@SessionAttribute(name = "loginAdmin", required = false)
                                            SessionAdmin sessionAdmin,
                                            HttpSession session) {
        if (sessionAdmin==null){
            return ResponseEntity.badRequest().build();
        }

        session.invalidate();
        return ResponseEntity.ok().build();
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

