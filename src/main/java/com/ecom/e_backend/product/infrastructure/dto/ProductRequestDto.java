package com.ecom.e_backend.product.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ProductRequestDto(

    @JsonProperty("public_id")
    UUID publicId,

    String name,

    String description,

    String brand,

    String color,

    double price,

    boolean featured,

    int stock,

    @JsonProperty("picture_url")
    String pictureUrl,

    @JsonProperty("category_public_id")
    UUID categoryPublicId
) {

    public Product toProduct(Long categoryId) {
        return Product.builder()
            .name(name())
            .description(description())
            .brand(brand())
            .color(color())
            .price(price())
            .featured(featured())
            .stock(stock())
            .pictureUrl(pictureUrl())
            .categoryId(categoryId)
            .build();
    }
    
}
