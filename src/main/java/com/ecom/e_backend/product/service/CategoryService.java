package com.ecom.e_backend.product.service;

import java.util.UUID;

import com.ecom.e_backend.product.dto.CategoryDto;
import com.ecom.e_backend.product.entity.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Mono<Category> save(CategoryDto categoryDto);

    Flux<CategoryDto> findAll();

    Mono<Void> deleteByPublicId(UUID publicId);

}
