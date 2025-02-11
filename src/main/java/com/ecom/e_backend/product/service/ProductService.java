package com.ecom.e_backend.product.service;

import java.util.UUID;

import com.ecom.e_backend.product.dto.ProductDto;
import com.ecom.e_backend.product.entity.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> save(ProductDto productDto);

    Flux<ProductDto> findAll();

    Mono<Void> deleteByPublicId(UUID publicId);

    Mono<ProductDto> findByPublicId(UUID publicId);

}
