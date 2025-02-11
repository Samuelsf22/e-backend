package com.ecom.e_backend.product.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.product.dto.CategoryDto;
import com.ecom.e_backend.product.entity.Category;
import com.ecom.e_backend.product.repository.CategoryRepository;
import com.ecom.e_backend.product.service.CategoryService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Mono<Category> save(CategoryDto categoryDto) {
        Category category = Category.builder()
                .publicId(UUID.randomUUID())
                .name(categoryDto.name())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Flux<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .map(category -> new CategoryDto(category.getPublicId(), category.getName()));
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return categoryRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No category found with public id: " + publicId)))
                .flatMap(category -> categoryRepository.deleteByPublicId(publicId));
    }

}
