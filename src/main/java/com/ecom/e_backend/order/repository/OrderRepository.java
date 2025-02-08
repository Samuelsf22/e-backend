package com.ecom.e_backend.order.repository;

import com.ecom.e_backend.order.OrderEntity;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderRepository extends R2dbcRepository<OrderEntity, Long> {}
