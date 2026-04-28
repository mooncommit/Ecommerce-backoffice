package com.example.ecommerce_backoffice.admin.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

// 관리자 다건 조회 응답 DTO
@Getter
@RequiredArgsConstructor
public class AdminPageResponseDto {

    private final int currentPage;
    private final int pageSize;
    private final long totalCount;
    private final int totalPages;
    private final List<AdminListResponseDto> adminList;

    public static AdminPageResponseDto from(Page<AdminListResponseDto> page) {
        return new AdminPageResponseDto(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }

}
