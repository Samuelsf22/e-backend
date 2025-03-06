package com.ecom.e_backend.user.domain;

import java.util.UUID;

import com.ecom.e_backend.user.domain.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAll();

    Mono<User> findByPublicId(UUID publicId);

    Mono<User> findByUsername(String username);

    Mono<User> update(UUID publicId, User user);

    Mono<Void> delete(UUID publicId);

}
