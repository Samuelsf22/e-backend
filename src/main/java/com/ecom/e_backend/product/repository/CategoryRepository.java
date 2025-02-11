package com.ecom.e_backend.product.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ecom.e_backend.product.entity.Category;

import reactor.core.publisher.Mono;

@Repository
public interface CategoryRepository extends R2dbcRepository<Category, Long> {

    Mono<Category> findByPublicId(UUID publicId);

    Mono<Void> deleteByPublicId(UUID publicId);

}
