package com.ecom.e_backend.order.application;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.order.domain.OrderStatus;
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
        return orderRepository.findAllByUserPublicId(userPublicId)
                .switchIfEmpty(Flux.error(new CustomException(HttpStatus.NOT_FOUND, "No orders found for user with public id: " + userPublicId)));
    }

    @Override
    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Mono<Void> updateStatusByPublicId(UUID publicId) {
        return orderRepository.updateStatusByPublicId(OrderStatus.PAID, publicId);
    }

    @Override
    public Flux<OrderedProduct> findAllOrderedProductsByOrderPublicId(UUID orderPublicId) {
        return orderedProductRepository.findAllByOrderPublicId(orderPublicId)
                .switchIfEmpty(Flux.error(new CustomException(HttpStatus.NOT_FOUND, "No products found for order with public id: " + orderPublicId)));
    }

}
