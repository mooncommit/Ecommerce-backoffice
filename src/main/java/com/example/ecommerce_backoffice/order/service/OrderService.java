package com.example.ecommerce_backoffice.order.service;

import com.example.ecommerce_backoffice.common.exception.*;
import com.example.ecommerce_backoffice.customer.entity.Customer;
import com.example.ecommerce_backoffice.customer.repository.CustomerRepository;
import com.example.ecommerce_backoffice.order.dto.*;
import com.example.ecommerce_backoffice.order.entity.Order;
import com.example.ecommerce_backoffice.order.entity.OrderItem;
import com.example.ecommerce_backoffice.order.enums.OrderStatus;
import com.example.ecommerce_backoffice.order.repository.OrderItemRepository;
import com.example.ecommerce_backoffice.order.repository.OrderRepository;
import com.example.ecommerce_backoffice.product.entity.Product;
import com.example.ecommerce_backoffice.product.enums.ProductStatus;
import com.example.ecommerce_backoffice.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto) {

        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        // 단종 상품 주문 불가
        if (product.getStatus() == ProductStatus.DISCONTINUED) {
            throw new DiscontinuedProductException();
        }

        // 품절 상품 주문 불가
        if (product.getStatus() == ProductStatus.SOLD_OUT) {
            throw new SoldOutProductException();
        }

        // 재고 부족 검증
        if (product.getStock() < requestDto.getQuantity()) {
            throw new OutOfStockException();
        }

        // 주문 번호 자동 생성
        String orderNumber = "ORD-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyymmdd"))
                + "-"
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        int totalPrice = product.getPrice() * requestDto.getQuantity();

        // Order 생성
        Order order = new Order(orderNumber, customer, null, totalPrice, OrderStatus.PREPARING);
        orderRepository.save(order);

        // OrderItem 생성
        OrderItem orderItem = new OrderItem(order, product, product.getName(), product.getPrice(), requestDto.getQuantity());
        orderItemRepository.save(orderItem);

        product.decreaseStock(requestDto.getQuantity());

        return OrderCreateResponseDto.from(order, orderItem);
    }

    @Transactional(readOnly = true)
    public OrderGetOneResponseDto getOne(Long orderId) {
        // 주문 조회
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        // 주문 아이템 조회
        OrderItem orderItem = orderItemRepository.findByOrder(order).orElseThrow(OrderItemNotFoundException::new);

        return OrderGetOneResponseDto.from(order, orderItem);
    }

    @Transactional(readOnly = true)
    public Page<OrderGetAllResponseDto> getAll(int page, int size, String keyword, OrderStatus status, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Order> orderPage = orderRepository.findAllWithFilters(keyword,status,pageable);

        return orderPage.map(order -> {
            OrderItem orderItem = orderItemRepository.findByOrder(order).orElseThrow(OrderItemNotFoundException::new);
            return OrderGetAllResponseDto.from(order, orderItem);
        });
    }

    @Transactional
    public void cancelOrder(Long orderId, @Valid OrderCancelRequestDto requestDto) {
        // 주문 조회
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        // 준비중 상태에서만 취소 가능
        if (order.getStatus() != OrderStatus.PREPARING) {
            throw new OrderCancelNotAllowedException();
        }

        // 주문 취소 처리
        order.cancel(requestDto.getCancelReason());

        //
    }
}
