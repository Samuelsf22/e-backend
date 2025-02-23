package com.ecom.e_backend.auth.domain;

import com.ecom.e_backend.security.entity.User;

import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<AuthToken> login(String username, String password);

    Mono<AuthToken> create(User user);
        
}
