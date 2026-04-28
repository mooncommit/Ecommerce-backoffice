package com.example.ecommerce_backoffice.admin.dto;

import com.example.ecommerce_backoffice.admin.enums.AdminStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 관리자 상태 수정 요청 DTO
@Getter
@NoArgsConstructor
public class AdminStatusUpdateRequestDto {

    @NotNull(message = "상태는 필수 선택입니다.")
    private AdminStatus status;
}
