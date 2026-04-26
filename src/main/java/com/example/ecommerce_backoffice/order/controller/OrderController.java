package com.example.ecommerce_backoffice.order.controller;

import com.example.ecommerce_backoffice.order.dto.*;
import com.example.ecommerce_backoffice.order.enums.OrderStatus;
import com.example.ecommerce_backoffice.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponseDto> createOrder(@Valid @RequestBody OrderCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(requestDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderGetOneResponseDto> getOneOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOne(orderId));
    }

    @GetMapping
    public ResponseEntity<Page<OrderGetAllResponseDto>> getAllOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return ResponseEntity.ok(orderService.getAll(page, size, keyword, status, sortBy, direction));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId, @Valid @RequestBody OrderCancelRequestDto requestDto) {
        orderService.cancelOrder(orderId, requestDto);
        return ResponseEntity.ok().build();
    }
}
