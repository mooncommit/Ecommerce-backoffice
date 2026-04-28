package com.example.ecommerce_backoffice.order.service;

import com.example.ecommerce_backoffice.admin.entity.Admin;
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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto, HttpSession session) {

        SessionAdmin sessionAdmin = (SessionAdmin) session.getAttribute("loginAdmin");
        Admin admin = adminRepository.findById(sessionAdmin.getId()).orElseThrow(AdminNotFoundException::new);

        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        if (product.getStatus() == ProductStatus.DISCONTINUED) {
            throw new DiscontinuedProductException();
        }

        if (product.getStatus() == ProductStatus.SOLD_OUT) {
            throw new SoldOutProductException();
        }

        if (product.getStock() < requestDto.getQuantity()) {
            throw new OutOfStockException();
        }

        // 주문 번호 자동 생성
        String orderNumber = "ORD-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + "-"
                + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        int totalPrice = product.getPrice() * requestDto.getQuantity();

        Order order = new Order(orderNumber, customer, admin, totalPrice, OrderStatus.PREPARING);
        orderRepository.save(order);

        OrderItem orderItem = new OrderItem(order, product, product.getName(), product.getPrice(), requestDto.getQuantity());
        orderItemRepository.save(orderItem);

        product.decreaseStock(requestDto.getQuantity());

        return OrderCreateResponseDto.from(order, orderItem);
    }

    @Transactional(readOnly = true)
    public OrderDetailResponseDto getOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        OrderItem orderItem = orderItemRepository.findByOrder(order).orElseThrow(OrderItemNotFoundException::new);

        return OrderDetailResponseDto.from(order, orderItem);
    }

    @Transactional(readOnly = true)
    public OrderPageResponseDto getOrderList(String keyword, OrderStatus status, Pageable pageable) {

        Page<Order> orderPage = orderRepository.findOrder(keyword,status,pageable);

        Page<OrderListResponseDto> dtoPage = orderPage.map(order -> {
            OrderItem orderItem = orderItemRepository.findByOrder(order)
                    .orElseThrow(OrderItemNotFoundException::new);
            return OrderListResponseDto.from(order, orderItem);
        });

        return OrderPageResponseDto.from(dtoPage);
    }

    @Transactional
    public void cancelOrder(Long orderId, @Valid OrderCancelRequestDto requestDto) {

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        // 준비중 상태에서만 취소 가능
        if (order.getStatus() != OrderStatus.PREPARING) {
            throw new OrderCancelNotAllowedException();
        }

        order.cancel(requestDto.getCancelReason());

        OrderItem orderItem = orderItemRepository.findByOrder(order).orElseThrow(OrderItemNotFoundException::new);

        Product product = productRepository.findById(orderItem.getProduct().getId()).orElseThrow(ProductNotFoundException::new);

        // 재고 복구
        product.restoreStock(orderItem.getQuantity());
    }

    @Transactional
    public void updateOrderStatus(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        // 취소된 주문은 상태 변경 불가
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new InvalidOrderStatusException();
        }

        /**
         *  상태 전환 순서
         *  PREPARING -> SHIPPING -> DELIVERED
         */

        if (order.getStatus() == OrderStatus.PREPARING) {
            order.updateStatus(OrderStatus.SHIPPING);
        } else if (order.getStatus() == OrderStatus.SHIPPING) {
            order.updateStatus(OrderStatus.DELIVERED);
        } else {
            throw new InvalidOrderStatusException();
        }
    }
}
