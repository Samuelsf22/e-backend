package com.ecom.e_backend.product.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.product.infrastructure.entity.ProductEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface R2dbcProductRepository extends R2dbcRepository<ProductEntity, Long> {

    Mono<ProductEntity> findByPublicId(UUID publicId);

    Mono<Void> deleteByPublicId(UUID publicId);

    @Query("SELECT p.* FROM api_product p " +
            "INNER JOIN api_category c ON p.category_id = c.id " +
            "WHERE c.public_id = :categoryPublicId")
    Flux<ProductEntity> findByCategoryPublicId(UUID categoryPublicId);

    @Query("SELECT * FROM api_product WHERE featured = true")
    Flux<ProductEntity> findAllFeaturedProducts();

    @Query("UPDATE api_product SET stock = stock - :quantity WHERE public_id = :publicId")
    Mono<Void> updateQuantity(UUID publicId, long quantity);

    Mono<Boolean> existsByPublicId(UUID publicId);

    @Query("SELECT p.* FROM api_product p " +
            "WHERE p.category_id = (SELECT category_id FROM api_product WHERE public_id = :publicId) " +
            "AND p.public_id != :publicId")
    Flux<ProductEntity> findRelatedProducts(UUID publicId);

}
