package com.ecom.e_backend.order.infrastructure.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.order.infrastructure.entity.OrderedProductEntity;

public interface R2dbcOrderedProductRepository extends R2dbcRepository<OrderedProductEntity, Long> {
    
}
