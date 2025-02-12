package com.ecom.e_backend.product.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
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

        Mono<Category> categoryMono = categoryRepository.findByName(productDto.category())
                .switchIfEmpty(Mono.error(new RuntimeException("Category " + productDto.category() + " not found")));

        Product product = Product.builder()
                .publicId(UUID.randomUUID())
                .name(productDto.name())
                .price(productDto.price())
                .description(productDto.description())
                .brand(productDto.brand())
                .color(productDto.color())
                .price(productDto.price())
                .featured(productDto.featured())
                .stock(productDto.stock())
                .category(categoryMono.block())
                .build();

        return productRepository.save(product);
    }

    @Override
    public Flux<ProductDto> findAll() {
        return productRepository.findAll().map(product -> ProductDto.builder()
                .publicId(product.getPublicId())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .color(product.getColor())
                .price(product.getPrice())
                .featured(product.isFeatured())
                .stock(product.getStock())
                .category(product.getCategory().getName())
                .build());
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return productRepository.deleteByPublicId(publicId)
                .switchIfEmpty(Mono.error(
                        new CustomException(HttpStatus.NOT_FOUND, "No product found with public id: " + publicId)));
    }

    @Override
    public Mono<ProductDto> findByPublicId(UUID publicId) {
        return productRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No product found with public id: " + publicId)))
                .map(product -> ProductDto.builder()
                        .publicId(product.getPublicId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .brand(product.getBrand())
                        .color(product.getColor())
                        .price(product.getPrice())
                        .featured(product.isFeatured())
                        .stock(product.getStock())
                        .category(product.getCategory().getName())
                        .build());
    }

}
