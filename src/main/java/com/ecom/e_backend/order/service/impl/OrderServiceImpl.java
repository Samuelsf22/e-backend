package com.ecom.e_backend.order.service.impl;

import com.ecom.e_backend.order.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.ecom.e_backend.order.OrderEntity;
import com.ecom.e_backend.order.controller.dto.OrderDto;
import com.ecom.e_backend.order.repository.OrderRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Flux<OrderDto> getOrders() {
        return null;
    }

    @Override
    public Mono<OrderDto> getOrderById(Long orderId) {
        return null;
    }

    @Override
    public Mono<OrderDto> createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public Mono<OrderDto> updateOrder(Long orderId, OrderDto orderDto) {
        return null;
    }

    @Override
    public Mono<Void> deleteOrder(Long orderId) {
        return null;
    }
}
