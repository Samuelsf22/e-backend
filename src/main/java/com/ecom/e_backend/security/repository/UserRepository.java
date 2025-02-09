package com.ecom.e_backend.security.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.ecom.e_backend.security.entity.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Long> {

    Mono<User> findByUsernameOrEmail(String username, String email);

}
