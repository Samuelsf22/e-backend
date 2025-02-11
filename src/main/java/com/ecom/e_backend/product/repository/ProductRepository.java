package com.ecom.e_backend.product.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.product.entity.Product;

public interface ProductRepository extends R2dbcRepository<Product, Long> {
    
}
