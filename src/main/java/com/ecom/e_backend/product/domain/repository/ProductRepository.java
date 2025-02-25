package com.ecom.e_backend.product.domain.repository;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> save(Product product);

    Flux<Product> findAll();

    Mono<Product> findByPublicId(UUID publicId);

    Flux<Product> findByCategoryPublicId(UUID categoryPublicId);

    Flux<Product> findAllFeaturedProducts();

    Mono<Void> deleteByPublicId(UUID publicId);

    Mono<Product> updateQuantity(UUID publicId, long quantity);

    Mono<Boolean> existsByPublicId(UUID publicId);
    
}
