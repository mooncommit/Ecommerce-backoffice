package com.example.ecommerce_backoffice.auth.dto;

import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import jakarta.validation.constraints.*;
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

    @Size(max = 20, message = "입력 한도가 초과 되었습니다.")
    @NotBlank(message = "이름 작성은 필수 입력입니다.")
    private String name;

    @NotBlank(message = "이메일 작성은 필수 입력입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 8,max = 255,message = "비밀번호는 최소 8글자 이상이어야 합니다.")
    @NotBlank(message = "비밀번호 입력은 필수입력입니다.")
    private String password;

    @NotBlank(message = "전화번호는 필수 입력입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$",
            message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
    private String phone;

    @NotNull(message = """
            운영자 역할은 필수 입력입니다.
            //슈퍼 관리자
            SUPER_ADMIN
            // 운영 관리자
            OPERATIONS_ADMIN
            // CS 관리자
            CS_ADMIN""")
    private AdminRole role;

}
