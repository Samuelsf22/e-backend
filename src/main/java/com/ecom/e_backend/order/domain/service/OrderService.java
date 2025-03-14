package com.ecom.e_backend.order.domain.service;

import java.util.UUID;

import com.ecom.e_backend.order.domain.models.Order;
import com.ecom.e_backend.order.domain.models.OrderedProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Void> createOrder(UUID userPublicId, Flux<OrderedProduct> products);

    Flux<Order> findAllByUserPublicId(UUID userPublicId);

    Flux<Order> findAll();

    Mono<Void> updateStatusByPublicId(UUID publicId);

    Flux<OrderedProduct> findAllOrderedProductsByOrderPublicId(UUID orderPublicId);

}
