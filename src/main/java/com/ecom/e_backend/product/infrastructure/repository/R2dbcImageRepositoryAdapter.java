package com.ecom.e_backend.product.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.ecom.e_backend.product.domain.models.Image;
import com.ecom.e_backend.product.domain.repository.ImageRepository;
import com.ecom.e_backend.product.infrastructure.entity.ImageEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class R2dbcImageRepositoryAdapter implements ImageRepository {

    private final R2dbcImageRepository r2dbcImageRepository;

    @Override
    public Mono<Image> save(Image image) {
        return r2dbcImageRepository.save(ImageEntity.toEntity(image)).map(ImageEntity::toDomain);
    }

    @Override
    public Mono<Void> delete(String publicId) {
        return r2dbcImageRepository.deleteByPublicId(publicId);
    }

}
