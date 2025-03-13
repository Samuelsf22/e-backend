package com.ecom.e_backend.product.infrastructure.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.product.infrastructure.entity.ImageEntity;

import reactor.core.publisher.Mono;

public interface R2dbcImageRepository extends R2dbcRepository<ImageEntity, Long> {

    Mono<Void> deleteByPublicId(String publicId);

}
