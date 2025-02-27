package com.ecom.e_backend.user.domain;

import java.util.UUID;

import com.ecom.e_backend.user.domain.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> findByPublicId(UUID publicId);

    Flux<User> findAll();

    Mono<User> findByUsername(String username);

    Mono<Boolean> existsByUsername(String username);

    Mono<User> save(User user);

    Mono<Void> deleteByPublicId(UUID publicId);

}
