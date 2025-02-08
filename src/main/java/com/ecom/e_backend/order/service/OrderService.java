package com.ecom.e_backend.order.service;

import com.ecom.e_backend.order.controller.dto.OrderDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Flux<OrderDto> getOrders();
    Mono<OrderDto> getOrderById(Long orderPublicId);
    Mono<OrderDto> createOrder(OrderDto orderDto);
    Mono<OrderDto> updateOrder(Long orderPublicId, OrderDto orderDto);
    Mono<Void> deleteOrder(Long orderPublicId);
}
