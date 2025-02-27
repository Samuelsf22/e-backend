package com.ecom.e_backend.product.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ecom.e_backend.product.domain.models.Category;
import com.ecom.e_backend.product.domain.repository.CategoryRepository;
import com.ecom.e_backend.product.infrastructure.entity.CategoryEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcCategoryRepositoryAdapter implements CategoryRepository {

    private final R2dbcCategoryRepository r2dbcCategoryRepository;

    @Override
    public Flux<Category> findAll() {
        return r2dbcCategoryRepository.findAll().map(CategoryEntity::toDomain);
    }

    @Override
    public Mono<Category> save(Category category) {
        return r2dbcCategoryRepository.save(CategoryEntity.toEntity(category)).map(CategoryEntity::toDomain);
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return r2dbcCategoryRepository.deleteByPublicId(publicId);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return r2dbcCategoryRepository.existsByName(name);
    }

    @Override
    public Mono<Category> findByPublicId(UUID publicId) {
        return r2dbcCategoryRepository.findByPublicId(publicId).map(CategoryEntity::toDomain);
    }


    
}
