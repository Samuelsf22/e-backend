package com.ecom.e_backend.product.domain.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.e_backend.product.domain.models.Image;

import reactor.core.publisher.Mono;

public interface ImageService {

    Mono<Image> save(MultipartFile file);

    Mono<Void> delete(UUID publicId);
    
}
