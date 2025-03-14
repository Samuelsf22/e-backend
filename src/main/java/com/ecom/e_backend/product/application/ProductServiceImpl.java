package com.ecom.e_backend.product.application;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.product.domain.models.Product;
import com.ecom.e_backend.product.domain.repository.ProductRepository;
import com.ecom.e_backend.product.domain.service.CloudStorageService;
import com.ecom.e_backend.product.domain.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final long MIN_QUANTITY = 1;

    private final ProductRepository productRepository;
    private final CloudStorageService cloudStorageService;

    @Override
    public Mono<Product> save(Product product, FilePart file) throws IOException {
        return cloudStorageService.save(file)
                .flatMap(uploadResult -> {
                    String imageUrl = uploadResult.get("url").toString();
                    String imagePublicId = uploadResult.get("public_id").toString();

                    product.setPublicId(UUID.randomUUID());
                    product.setImageUrl(imageUrl);
                    product.setImagePublicId(imagePublicId);
                    product.setImageName(file.filename());

                    return productRepository.save(product);
                });
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
        return productRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Product not found")))
                .flatMap(product -> cloudStorageService.delete(product.getImagePublicId())
                        .then(productRepository.deleteByPublicId(publicId)));
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
    public Mono<Void> updateQuantity(UUID publicId, long quantity) {
        if (quantity < MIN_QUANTITY) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Quantity must be greater than 0"));
        }
        return productRepository.existsByPublicId(publicId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Product not found"));
                    }
                    return productRepository.updateQuantity(publicId, quantity);
                });
    }

    @Override
    public Flux<Product> findRelatedProducts(UUID publicId) {
        return productRepository.findRelatedProducts(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No related products found")));
    }

}
