package com.ecom.e_backend.product.application;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.product.domain.models.Product;
import com.ecom.e_backend.product.domain.repository.ProductRepository;
import com.ecom.e_backend.product.domain.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> findByPublicId(UUID publicId) {
        return productRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Void> deleteByPublicId(UUID publicId) {
        return productRepository.existsByPublicId(publicId)
                .flatMap(exists -> exists
                        ? productRepository.deleteByPublicId(publicId)
                        : Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Product not found")));
    }

    @Override
    public Flux<Product> findByCategoryPublicId(UUID categoryPublicId) {
        return productRepository.findByCategoryPublicId(categoryPublicId)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No products found in this category")));
    }

    @Override
    public Flux<Product> findAllFeaturedProducts() {
        return productRepository.findAllFeaturedProducts();
    }

    @Override
    public Mono<Product> updateQuantity(UUID publicId, long quantity) {
        return productRepository.updateQuantity(publicId, quantity)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Product not found")));

    }

}
