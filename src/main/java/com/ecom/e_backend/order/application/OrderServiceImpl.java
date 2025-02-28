package com.ecom.e_backend.order.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ecom.e_backend.order.domain.models.Order;
import com.ecom.e_backend.order.domain.models.OrderedProduct;
import com.ecom.e_backend.order.domain.repository.OrderRepository;
import com.ecom.e_backend.order.domain.repository.OrderedProductRepository;
import com.ecom.e_backend.order.domain.service.OrderService;
import com.ecom.e_backend.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderedProductRepository orderedProductRepository;

    @Override
    public Mono<Void> createOrder(UUID userPublicId, Flux<OrderedProduct> products) {
        return userRepository.findByPublicId(userPublicId)
                .map(user -> user.getId())
                .map(userId -> Order.create(userId))
                .flatMap(order -> orderRepository.save(order)
                .flatMap(savedOrder -> orderedProductRepository
                        .saveAll(products.map(pq -> OrderedProduct.builder()
                                .orderId(savedOrder.getId())
                                .productPublicId(pq.getProductPublicId())
                                .price(pq.getPrice())
                                .quantity(pq.getQuantity())
                                .productName(pq.getProductName())
                                .build()))
                        .then()));
    }

    @Override
    public Flux<Order> findAllByUserPublicId(UUID userPublicId) {
        throw new UnsupportedOperationException("Unimplemented method 'findAllByUserPublicId'");
    }

    @Override
    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Flux<OrderedProduct> updateStatusByPublicId(UUID publicId) {
        throw new UnsupportedOperationException("Unimplemented method 'updateStatusByPublicId'");
    }

}
