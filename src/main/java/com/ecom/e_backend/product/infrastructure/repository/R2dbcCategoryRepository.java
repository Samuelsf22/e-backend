package com.ecom.e_backend.product.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.product.infrastructure.entity.CategoryEntity;

import reactor.core.publisher.Mono;

public interface R2dbcCategoryRepository extends R2dbcRepository<CategoryEntity, Long> {

    Mono<CategoryEntity> findByPublicId(UUID publicId);

    Mono<Void> deleteByPublicId(UUID publicId);

    Mono<Boolean> existsByName(String name);
    
}
