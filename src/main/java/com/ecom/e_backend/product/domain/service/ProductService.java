package com.ecom.e_backend.product.domain.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.codec.multipart.FilePart;

import com.ecom.e_backend.product.domain.models.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> save(Product product, FilePart file) throws IOException;

    Mono<Product> findByPublicId(UUID publicId);

    Flux<Product> findAll();

    Mono<Void> deleteByPublicId(UUID publicId);

    Flux<Product> findByCategoryPublicId(UUID categoryPublicId);

    Flux<Product> findAllFeaturedProducts();

    Mono<Void> updateQuantity(UUID publicId, long quantity);

    Flux<Product> findRelatedProducts(UUID publicId);

}
