package com.example.ecommerce_backoffice.order.controller;

import com.example.ecommerce_backoffice.order.dto.OrderCreateRequestDto;
import com.example.ecommerce_backoffice.order.dto.OrderCreateResponseDto;
import com.example.ecommerce_backoffice.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponseDto> createOrder(@Valid @RequestBody OrderCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(requestDto));
    }
}
