package com.ecom.e_backend.security.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.security.dto.CreateUserDto;
import com.ecom.e_backend.security.dto.LoginDto;
import com.ecom.e_backend.security.dto.TokenDto;
import com.ecom.e_backend.security.entity.User;
import com.ecom.e_backend.security.service.UserService;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {

    private final UserService userService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.login(dto), TokenDto.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreateUserDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.create(dto), User.class));
    }

}
