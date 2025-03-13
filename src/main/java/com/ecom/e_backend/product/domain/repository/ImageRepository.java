package com.ecom.e_backend.product.domain.repository;

import com.ecom.e_backend.product.domain.models.Image;

import reactor.core.publisher.Mono;

public interface ImageRepository {

    Mono<Image> save(Image image);

    Mono<Void> delete(String publicId);

}
