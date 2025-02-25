package com.ecom.e_backend.user.infrastructure.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.user.domain.UserService;
import com.ecom.e_backend.user.domain.models.User;
import com.ecom.e_backend.user.infrastructure.dto.UserResponseDto;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> findAllUsers(ServerRequest request) {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(userService.findAll().map(UserResponseDto::fromUser), UserResponseDto.class); 
    }

    public Mono<ServerResponse> findUserById(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findByPublicId(publicId).map(UserResponseDto::fromUser), UserResponseDto.class);
    }

    // public Mono<ServerResponse> updateUser(ServerRequest request) {
    //     UUID publicId = UUID.fromString(request.queryParam("public_id").get());
    //     return request.bodyToMono(UpdateUserDto.class).doOnNext(objectValidator::validate)
    //             .flatMap(user -> ServerResponse.ok()
    //                     .body(userService.update(publicId,
    //                             User.builder()
    //                                     .firstName(user.firstName())
    //                                     .lastName(user.lastName())
    //                                     .username(user.username())
    //                                     .password(user.password())
    //                                     .build()),
    //                             User.class));
    // }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .body(userService.delete(publicId), Void.class);
    }

}
