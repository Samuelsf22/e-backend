package com.ecom.e_backend.order.domain.repository;

import java.util.UUID;

import com.ecom.e_backend.order.domain.OrderStatus;
import com.ecom.e_backend.order.domain.models.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {

    Mono<Order> save(Order order);

    Mono<Void> updateStatusByPublicId(OrderStatus status, UUID publicId);
    
    Flux<Order> findAllByUserPublicId(UUID userPublicId);

    Flux<Order> findAll();

}
