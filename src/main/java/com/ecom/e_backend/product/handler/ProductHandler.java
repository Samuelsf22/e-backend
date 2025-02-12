package com.ecom.e_backend.product.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.dto.ProductDto;
import com.ecom.e_backend.product.entity.Product;
import com.ecom.e_backend.product.service.ProductService;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(ProductDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.save(dto), Product.class));
    }
    

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }

    public Mono<ServerResponse> deleteByPublicId(ServerRequest request) {
        UUID publicId = UUID.fromString(request.pathVariable("public_id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.deleteByPublicId(publicId), Void.class);
    }

    public Mono<ServerResponse> findByPublicId(ServerRequest request) {
        UUID publicId = UUID.fromString(request.pathVariable("public_id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findByPublicId(publicId), Product.class);
    }

    public Mono<ServerResponse> updateQuantity(ServerRequest request) {
        UUID publicId = UUID.fromString(request.pathVariable("public_id"));
        int quantity = Integer.parseInt(request.pathVariable("quantity"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.updateQuantity(publicId, quantity), Product.class);
    }
}
