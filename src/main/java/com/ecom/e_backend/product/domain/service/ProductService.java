package com.ecom.e_backend.product.domain.service;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> save(Product product);

    Mono<Product> findByPublicId(UUID publicId);

    Flux<Product> findAll();

    Mono<Void> deleteByPublicId(UUID publicId);

    Flux<Product> findByCategoryPublicId(UUID categoryPublicId);

    Flux<Product> findAllFeaturedProducts();

    Mono<Void> updateQuantity(UUID publicId, long quantity);

    Flux<Product> findRelatedProducts(UUID publicId);
    
}
