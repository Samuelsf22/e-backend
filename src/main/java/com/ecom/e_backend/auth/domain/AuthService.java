package com.ecom.e_backend.auth.domain;

import com.ecom.e_backend.user.domain.models.User;

import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<AuthToken> login(String username, String password);

    Mono<AuthToken> create(User user);
        
}
