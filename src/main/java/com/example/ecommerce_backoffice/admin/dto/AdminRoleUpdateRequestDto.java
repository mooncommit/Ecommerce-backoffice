package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.enums.AdminRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 관리자 역할 수정 요청 DTO
@Getter
@NoArgsConstructor
public class AdminRoleUpdateRequestDto {

    @NotNull(message = "역할은 필수 선택입니다.")
    private AdminRole role;

}
