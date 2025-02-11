package com.ecom.e_backend.product.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.dto.CategoryDto;
import com.ecom.e_backend.product.entity.Category;
import com.ecom.e_backend.product.service.CategoryService;
import com.ecom.e_backend.security.enums.Role;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final CategoryService categoryService;

    private final ObjectValidator objectValidator;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(CategoryDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(categoryService.save(dto), Category.class));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.findAll(), Category.class);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Mono<ServerResponse> deleteByPublicId(ServerRequest request) {
        UUID publicId = UUID.fromString(request.pathVariable("public_id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.deleteByPublicId(publicId), Void.class);
    }
}
