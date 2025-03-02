package com.ecom.e_backend.order.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.order.infrastructure.entity.OrderEntity;

import reactor.core.publisher.Flux;

public interface R2dbcOrderRepository extends R2dbcRepository<OrderEntity, Long> {

    @Query("SELECT * FROM api_order o " +
            "JOIN api_user u ON o.user_id = u.id " +
            "WHERE u.public_id = :userPublicId")
    Flux<OrderEntity> findAllByUserPublicId(UUID userPublicId);

}
