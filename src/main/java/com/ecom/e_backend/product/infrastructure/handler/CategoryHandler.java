package com.ecom.e_backend.product.infrastructure.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.domain.service.CategoryService;
import com.ecom.e_backend.product.infrastructure.dto.CategoryRequestDto;
import com.ecom.e_backend.product.infrastructure.dto.CategoryResponseDto;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final CategoryService categoryService;

    private final ObjectValidator objectValidator;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(CategoryRequestDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(categoryService.save(dto.toCategory()),
                                CategoryResponseDto.class));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.findAll().map(CategoryResponseDto::fromCategory), CategoryResponseDto.class);
    }

    public Mono<ServerResponse> findByPublicId(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.findByPublicId(publicId).map(CategoryResponseDto::fromCategory), CategoryResponseDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> delete(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.delete(publicId), Void.class);
    }
}
