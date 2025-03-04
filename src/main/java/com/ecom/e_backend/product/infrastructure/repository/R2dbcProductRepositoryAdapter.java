package com.ecom.e_backend.product.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ecom.e_backend.product.domain.models.Product;
import com.ecom.e_backend.product.domain.repository.ProductRepository;
import com.ecom.e_backend.product.infrastructure.entity.ProductEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcProductRepositoryAdapter implements ProductRepository {

    private final R2dbcProductRepository r2dbcProductRepository;

    @Override
    public Mono<Product> save(Product product) {
        return r2dbcProductRepository.save(ProductEntity.toEntity(product)).map(ProductEntity::toDomain);
    }

    @Override
    public Mono<Product> findByPublicId(UUID publicId) {
        return r2dbcProductRepository.findByPublicId(publicId).map(ProductEntity::toDomain);
    }

    @Override
    public Flux<Product> findAll() {
        return r2dbcProductRepository.findAll().map(ProductEntity::toDomain);
    }

    @Override
    public Flux<Product> findByCategoryPublicId(UUID categoryPublicId) {
        return r2dbcProductRepository.findByCategoryPublicId(categoryPublicId).map(ProductEntity::toDomain);
    }

    @Override
    public Flux<Product> findAllFeaturedProducts() {
        return r2dbcProductRepository.findAllFeaturedProducts().map(ProductEntity::toDomain);
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return r2dbcProductRepository.deleteByPublicId(publicId);
    }

    @Override
    public Mono<Void> updateQuantity(UUID publicId, long quantity) {
        return r2dbcProductRepository.updateQuantity(publicId, quantity);
    }

    @Override
    public Mono<Boolean> existsByPublicId(UUID publicId) {
        return r2dbcProductRepository.existsByPublicId(publicId);
    }

    @Override
    public Flux<Product> findRelatedProducts(UUID publicId) {
        return r2dbcProductRepository.findRelatedProducts(publicId).map(ProductEntity::toDomain);
    }


}
