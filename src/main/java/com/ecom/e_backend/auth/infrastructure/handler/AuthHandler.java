package com.ecom.e_backend.auth.infrastructure.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.auth.domain.AuthService;
import com.ecom.e_backend.auth.domain.AuthToken;
import com.ecom.e_backend.auth.infrastructure.dto.RequestUserDto;
import com.ecom.e_backend.auth.infrastructure.dto.LoginDto;
import com.ecom.e_backend.security.entity.User;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {

    private final AuthService authService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(authService.login(dto.username(), dto.password()), AuthToken.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(RequestUserDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(authService.create(
                                User.builder()
                                        .firstName(dto.firstName())
                                        .lastName(dto.lastName())
                                        .username(dto.username())
                                        .password(dto.password())
                                        .address(dto.address())
                                        .build()),
                                AuthToken.class));
    }

}
