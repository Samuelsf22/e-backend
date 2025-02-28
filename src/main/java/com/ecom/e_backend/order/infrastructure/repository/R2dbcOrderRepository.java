package com.ecom.e_backend.order.infrastructure.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.order.infrastructure.entity.OrderEntity;

public interface R2dbcOrderRepository extends R2dbcRepository<OrderEntity, Long> {

   
}
