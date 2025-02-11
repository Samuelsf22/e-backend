package com.ecom.e_backend.product.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ecom.e_backend.product.dto.ProductDto;
import com.ecom.e_backend.product.entity.Category;
import com.ecom.e_backend.product.entity.Product;
import com.ecom.e_backend.product.repository.CategoryRepository;
import com.ecom.e_backend.product.repository.ProductRepository;
import com.ecom.e_backend.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Mono<Product> save(ProductDto productDto) {
        return null;
    }

    @Override
    public Flux<ProductDto> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return null;
    }

    @Override
    public Mono<ProductDto> findByPublicId(UUID publicId) {
        return null;
    }

}
