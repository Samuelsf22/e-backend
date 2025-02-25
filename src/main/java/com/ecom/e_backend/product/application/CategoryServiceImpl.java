package com.ecom.e_backend.product.application;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.product.domain.models.Category;
import com.ecom.e_backend.product.domain.repository.CategoryRepository;
import com.ecom.e_backend.product.domain.service.CategoryService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> save(Category category) {
        return categoryRepository.existsByName(category.getName())
                .flatMap(exists -> {
                    if (exists)
                        return Mono.error(new CustomException(HttpStatus.CONFLICT, "Category already exists"));

                    category.setPublicId(UUID.randomUUID());
                    return categoryRepository.save(category);
                });
    }

    @Override
    public Mono<Void> delete(UUID publicId) {
        return categoryRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(
                        new CustomException(HttpStatus.NOT_FOUND, "Category not found")))
                .flatMap(category -> categoryRepository.deleteByPublicId(publicId));
    }

    @Override
    public Mono<Category> findByPublicId(UUID publicId) {
        return categoryRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(
                        new CustomException(HttpStatus.NOT_FOUND, "Category not found")));
    }

}
