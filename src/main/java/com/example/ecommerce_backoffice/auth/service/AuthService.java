package com.example.ecommerce_backoffice.auth.service;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.auth.config.PasswordEncoder;
import com.example.ecommerce_backoffice.auth.dto.RegisterCreateRequestDto;
import com.example.ecommerce_backoffice.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 이메일 중복여부 확인하고 중복시 적절한 에러 반환
     * 비밀번호 암호화 해서 저장
     * 등록일 자동으로 현재 시간으로 설정
     * 회원가입시 승인대기 상태 부여
     */


    @Transactional
    public Admin createRegister(RegisterCreateRequestDto request) {
        if (authRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());


        Admin admin = new Admin(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhone(),
                request.getRole(),
                AdminStatus.PENDING

        );

        return authRepository.save(admin);
    }


}
