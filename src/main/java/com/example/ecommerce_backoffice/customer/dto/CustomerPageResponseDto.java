package com.example.ecommerce_backoffice.customer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomerPageResponseDto {

    private final int currentPage;
    private final int pageSize;
    private final long totalCount;
    private final int totalPages;
    private final List<CustomerListResponseDto> customerList;


    public static CustomerPageResponseDto from(Page<CustomerListResponseDto> page) {
        return new CustomerPageResponseDto(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent());
    }
}
