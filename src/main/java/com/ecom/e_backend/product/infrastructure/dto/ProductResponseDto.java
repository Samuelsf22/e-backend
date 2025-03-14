package com.ecom.e_backend.product.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ProductResponseDto(

    @JsonProperty("public_id")
    UUID publicId,

    String name,

    String description,

    String brand,

    String color,

    double price,

    boolean featured,

    int stock,

    @JsonProperty("image_url")
    String imageUrl

) {

    public static ProductResponseDto fromProduct(Product product) {
        return ProductResponseDto.builder()
            .publicId(product.getPublicId())
            .name(product.getName())
            .description(product.getDescription())
            .brand(product.getBrand())
            .color(product.getColor())
            .price(product.getPrice())
            .featured(product.isFeatured())
            .stock(product.getStock())
            .imageUrl(product.getImageUrl())
            .build();
    }
    
}
