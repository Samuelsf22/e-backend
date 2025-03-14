package com.ecom.e_backend.order.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.order.infrastructure.entity.OrderedProductEntity;

import reactor.core.publisher.Flux;

public interface R2dbcOrderedProductRepository extends R2dbcRepository<OrderedProductEntity, Long> {

    @Query("SELECT * FROM api_ordered_product op " +
            "JOIN api_order o ON op.order_id = o.id " +
            "WHERE o.public_id = :orderPublicId")
    Flux<OrderedProductEntity> findAllByOrderPublicId(UUID orderPublicId);

}
