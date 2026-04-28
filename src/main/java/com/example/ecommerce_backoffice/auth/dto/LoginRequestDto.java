package com.example.ecommerce_backoffice.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequestDto {



    //속성
            @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email;
            @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;

}
