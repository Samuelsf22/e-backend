package com.ecom.e_backend.user.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ecom.e_backend.user.domain.UserRepository;
import com.ecom.e_backend.user.domain.models.User;
import com.ecom.e_backend.user.infrastructure.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcUserRepositoryAdapter implements UserRepository {

    private final R2dbcUserRepository r2dbcUserRepository;

    @Override
    public Mono<User> findByPublicId(UUID publicId) {
        return r2dbcUserRepository.findByPublicId(publicId).map(UserEntity::toDomain);
    }

    @Override
    public Flux<User> findAll() {
        return r2dbcUserRepository.findAll().map(UserEntity::toDomain);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return r2dbcUserRepository.findByUsername(username).map(UserEntity::toDomain);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return r2dbcUserRepository.existsByUsername(username);
    }

    @Override
    public Mono<User> save(User user) {
        return r2dbcUserRepository.save(UserEntity.toEntity(user)).map(UserEntity::toDomain);
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return r2dbcUserRepository.deleteByPublicId(publicId);
    }

}
