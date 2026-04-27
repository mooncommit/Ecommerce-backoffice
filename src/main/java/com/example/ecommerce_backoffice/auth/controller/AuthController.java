package com.example.ecommerce_backoffice.auth.controller;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.auth.dto.LoginRequestDto;
import com.example.ecommerce_backoffice.auth.dto.SignupCreateRequestDto;
import com.example.ecommerce_backoffice.auth.dto.SessionAdmin;
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
    //속성
    private final AuthService authService;

    //기능

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid @RequestBody SignupCreateRequestDto request) {
        authService.createRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("관리자등록이 완료되었습니다. 슈퍼 관리자의 승인이 있어야 로그인 가능합니다.");
    }


    /**
     * HttpSession session : 컨트롤러 메소드의 파라미터.
     *  -> HttpSession 을 선언하면, 스프링이 현재 요청에 해당하는
     *     세션 객체를 자동으로 주입해줌
     *
     * session.setAttribute("loginUser", sessionUser)
     *  -> "로그인 상태를 만드는" 핵심 로직
     *  -> "loginUser"라는 key로, sessionUser객체를 세션 저장소에
     *      연결함
     *  -> 이제부터 이 사용자의 세션에는 loginUser의 정보가 계속
     *      남아있게 됨
     */
    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@Valid @RequestBody LoginRequestDto request, HttpSession session ) {
        Admin admin = authService.loginAdmin(request);
        SessionAdmin sessionAdmin = new SessionAdmin(admin);
        session.setAttribute("loginAdmin", sessionAdmin);

        return ResponseEntity.ok("로그인 성공!");
    }

    @PostMapping("/logout")
    //void
    //메서드 자체가 아무것도 반환하지 않을 때 쓰는 키워드
    //Void
    //제네릭에서 “바디 없음” 표현할 때 쓰는 타입
    public ResponseEntity<Void> adminLogout(@SessionAttribute(name = "loginAdmin", required = false)
                                            SessionAdmin sessionAdmin, //서랍 안에 들어 있는 로그인 메모
                                            HttpSession session) {     //그 서랍 자체
        if (sessionAdmin==null){
            return ResponseEntity.badRequest().build();
        }

        session.invalidate();
        return ResponseEntity.ok().build();
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

