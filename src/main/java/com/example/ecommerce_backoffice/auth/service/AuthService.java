package com.example.ecommerce_backoffice.auth.service;

import com.example.ecommerce_backoffice.admin.entity.Admin;
import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import com.example.ecommerce_backoffice.auth.config.PasswordEncoder;
import com.example.ecommerce_backoffice.auth.dto.LoginRequestDto;
import com.example.ecommerce_backoffice.auth.dto.RegisterCreateRequestDto;
import com.example.ecommerce_backoffice.auth.repository.AuthRepository;
import com.example.ecommerce_backoffice.common.exception.AdminNotApprovedException;
import com.example.ecommerce_backoffice.common.exception.InvalidPasswordException;
import jakarta.validation.Valid;
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
                encodedPassword,
                request.getPhone(),
                request.getRole(),
                AdminStatus.PENDING

        );

        return authRepository.save(admin);
    }

    /**
     * 로그인 실패 처리
     * 이메일 또는 비밀번호 불일치
     * 꼐정 승인대기중 PENDING
     * 계정 신청 거부됨 REJECTED
     * 계정 정지됨 SUSPENDED
     * 꼐정 비활성화됨 INACTIVE
     */

    @Transactional(readOnly = true)
    public Admin loginAdmin(@Valid LoginRequestDto request) {
        Admin admin = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidPasswordException());

        // 암호화를 했기 때문에 패스워드인코더에서 입력된 비번과 어드민조회비번이랑 매치가 될때만 통과
        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new InvalidPasswordException();
        }

        //익셉션 엔티티에 찾아봐도 안보여서 일단 일리걸아규먼트익셉션 사용헷슴미다.
        if (admin.getStatus() != AdminStatus.ACTIVE) {
            switch (admin.getStatus()) {
                case INACTIVE -> throw new IllegalArgumentException("비활성화된 계정");
                case SUSPENDED -> throw new IllegalArgumentException("정지된 계정");
                case PENDING -> throw new AdminNotApprovedException();
                case REJECTED -> throw new IllegalArgumentException("거부된 계정");
            }
        }



        return admin;
    }
}
