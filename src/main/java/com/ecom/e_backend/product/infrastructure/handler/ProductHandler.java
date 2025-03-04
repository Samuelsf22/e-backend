package com.ecom.e_backend.product.infrastructure.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.domain.service.CategoryService;
import com.ecom.e_backend.product.domain.service.ProductService;
import com.ecom.e_backend.product.infrastructure.dto.ProductRequestDto;
import com.ecom.e_backend.product.infrastructure.dto.ProductResponseDto;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final ObjectValidator objectValidator;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(ProductRequestDto.class)
                .doOnNext(objectValidator::validate)
                .flatMap(dto -> categoryService.findByPublicId(dto.categoryPublicId())
                        .flatMap(category -> productService.save(dto.toProduct(category.getId()))
                                .map(ProductResponseDto::fromProduct)
                                .flatMap(productResponse -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(productResponse))));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll().map(ProductResponseDto::fromProduct), ProductResponseDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> deleteByPublicId(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.deleteByPublicId(publicId), Void.class);
    }

    public Mono<ServerResponse> findByPublicId(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findByPublicId(publicId).map(ProductResponseDto::fromProduct),
                        ProductResponseDto.class);
    }

    public Mono<ServerResponse> findByCategoryPublicId(ServerRequest request) {
        UUID categoryId = UUID.fromString(request.queryParam("category_public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findByCategoryPublicId(categoryId).map(ProductResponseDto::fromProduct),
                        ProductResponseDto.class);
    }

    public Mono<ServerResponse> findAllByFeatured(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAllFeaturedProducts().map(ProductResponseDto::fromProduct), ProductResponseDto.class);
    }

    public Mono<ServerResponse> findRelatedProducts(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findRelatedProducts(publicId).map(ProductResponseDto::fromProduct),
                        ProductResponseDto.class);
    }

    public Mono<ServerResponse> updateQuantity(ServerRequest request) {
        UUID publicId = UUID.fromString(request.queryParam("public_id").get());
        int quantity = Integer.parseInt(request.queryParam("quantity").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.updateQuantity(publicId, quantity), Void.class);
    }
}
