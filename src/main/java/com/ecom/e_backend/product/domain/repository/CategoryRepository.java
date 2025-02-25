package com.ecom.e_backend.product.domain.repository;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryRepository {

    Flux<Category> findAll();

    Mono<Category> save(Category category);

    Mono<Void> deleteByPublicId(UUID publicId);

    Mono<Boolean> existsByName(String name);

    Mono<Category> findByPublicId(UUID publicId);

}
