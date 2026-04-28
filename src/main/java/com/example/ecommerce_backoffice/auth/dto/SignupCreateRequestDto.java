package com.example.ecommerce_backoffice.auth.dto;

import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupCreateRequestDto {

    /**
     * 이름
     * 이메일
     * 비밀번호
     * 전화번호
     * 역할 - 슈퍼 관리자, 운영 관리자, cs 관리자
     */

    @NotBlank(message = "이름 작성은 필수 입력입니다.")
    private String name;

    @NotBlank(message = "이메일 작성은 필수 입력입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입력입니다.")
    private String password;

    @NotBlank(message = "전화번호는 필수 입력입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$",
            message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
    private String phone;

    private AdminRole role;


}
