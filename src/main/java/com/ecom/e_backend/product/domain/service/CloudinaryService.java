package com.ecom.e_backend.product.domain.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface CloudinaryService {

    Mono<Map> save(MultipartFile file);

    Mono<Map> delete(String publicId);

}
