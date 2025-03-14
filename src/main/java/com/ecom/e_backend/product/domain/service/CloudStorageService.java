package com.ecom.e_backend.product.domain.service;

import java.util.Map;

import org.springframework.http.codec.multipart.FilePart;

import reactor.core.publisher.Mono;

public interface CloudStorageService {

    Mono<Map> save(FilePart file);

    Mono<Map> delete(String publicId);

}
