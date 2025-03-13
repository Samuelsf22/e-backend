package com.ecom.e_backend.product.domain.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.codec.multipart.FilePart;

import reactor.core.publisher.Mono;

public interface CloudinaryService {

    Mono<Map> save(FilePart file) throws IOException;

    Mono<Map> delete(String publicId);

}
