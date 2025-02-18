package com.ecom.e_backend.product.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.product.entity.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductRepository extends R2dbcRepository<Product, Long> {

    Mono<Product> findByPublicId(UUID publicId);

    Mono<Void> deleteByPublicId(UUID publicId);

    Flux<Product> findByCategoryId(Long categoryId);

    Flux<Product> findAllByFeaturedTrue();

    @Query("UPDATE api_product SET stock = stock - :quantity WHERE public_id = :publicId")
    Mono<Void> updateQuantity(UUID publicId, int quantity);

}
