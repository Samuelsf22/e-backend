package com.ecom.e_backend.user.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.user.infrastructure.entity.UserEntity;

import reactor.core.publisher.Mono;

public interface R2dbcUserRepository extends R2dbcRepository<UserEntity, Long> {

    Mono<UserEntity> findByUsername(String username);

    Mono<Boolean> existsByUsername(String username);

    Mono<UserEntity> findByPublicId(UUID publicId);

    Mono<Void> deleteByPublicId(UUID publicId);

}
