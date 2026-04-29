package com.example.ecommerce_backoffice.order.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderPageResponseDto {

    private final int currentPage;
    private final int pageSize;
    private final Long totalCount;
    private final int totalPages;
    private final List<Object> orderList;

    public static OrderPageResponseDto from(Page<Object> page) {
        return new OrderPageResponseDto(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }
}
