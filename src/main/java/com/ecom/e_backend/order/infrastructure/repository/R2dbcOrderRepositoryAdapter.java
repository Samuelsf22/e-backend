package com.ecom.e_backend.order.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ecom.e_backend.order.domain.OrderStatus;
import com.ecom.e_backend.order.domain.models.Order;
import com.ecom.e_backend.order.domain.repository.OrderRepository;
import com.ecom.e_backend.order.infrastructure.entity.OrderEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcOrderRepositoryAdapter implements OrderRepository{

    private final R2dbcOrderRepository r2dbcOrderRepository;

    @Override
    public Mono<Order> save(Order order) {
        return r2dbcOrderRepository.save(OrderEntity.toEntity(order)).map(OrderEntity::toDomain);
    }

    @Override
    public Mono<Void> updateStatusByPublicId(OrderStatus status, UUID publicId) {
        throw new UnsupportedOperationException("Unimplemented method 'updateStatusByPublicId'");
    }

    @Override
    public Flux<Order> findAllByUserPublicId(UUID userPublicId) {
        throw new UnsupportedOperationException("Unimplemented method 'findAllByUserPublicId'");
    }

    @Override
    public Flux<Order> findAll() {
        return r2dbcOrderRepository.findAll().map(OrderEntity::toDomain);
    }
    
}
