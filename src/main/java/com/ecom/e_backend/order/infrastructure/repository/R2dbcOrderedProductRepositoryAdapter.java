package com.ecom.e_backend.order.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.ecom.e_backend.order.domain.models.OrderedProduct;
import com.ecom.e_backend.order.domain.repository.OrderedProductRepository;
import com.ecom.e_backend.order.infrastructure.entity.OrderedProductEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class R2dbcOrderedProductRepositoryAdapter implements OrderedProductRepository {

    private final R2dbcOrderedProductRepository r2dbcOrderedProductRepository;

    @Override
    public Flux<OrderedProduct> saveAll(Flux<OrderedProduct> products) {
        return r2dbcOrderedProductRepository.saveAll(products.map(OrderedProductEntity::toEntity)).map(OrderedProductEntity::toDomain);
    }
    
}
