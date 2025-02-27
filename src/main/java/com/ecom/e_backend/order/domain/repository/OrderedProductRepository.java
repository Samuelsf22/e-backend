package com.ecom.e_backend.order.domain.repository;

import com.ecom.e_backend.order.domain.models.OrderedProduct;

import reactor.core.publisher.Flux;

public interface OrderedProductRepository {

    Flux<OrderedProduct> saveAll(Flux<OrderedProduct> products);

}
