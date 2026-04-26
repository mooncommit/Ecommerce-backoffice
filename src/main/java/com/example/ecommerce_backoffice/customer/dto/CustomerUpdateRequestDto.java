package com.example.ecommerce_backoffice.customer.dto;

import jakarta.validation.constraints.*;

public class CustomerUpdateRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 20, message = "최대 20자까지 입력 가능합니다.")
    private String name;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 255, message = "최대 255자까지 입력 가능합니다.")
    private String email;

    @Pattern(regexp = "010-\\d{4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    @Size(max = 20, message = "최대 20자까지 입력 가능합니다.")
    private String phone;



    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
}
