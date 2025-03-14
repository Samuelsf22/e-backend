package com.ecom.e_backend.product.domain.service;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> findAll();

    Mono<Category> save(Category category);

    Mono<Void> delete(UUID publicId);

    Mono<Category> findByPublicId(UUID publicId);
    
}
