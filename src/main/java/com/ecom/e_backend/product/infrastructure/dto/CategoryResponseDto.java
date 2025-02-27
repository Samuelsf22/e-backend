package com.ecom.e_backend.product.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record CategoryResponseDto(

    @JsonProperty("public_id")
    UUID publicId,

    String name
    
) {
    
    public static CategoryResponseDto fromCategory(Category category) {
        return CategoryResponseDto.builder()
            .publicId(category.getPublicId())
            .name(category.getName())
            .build();
    }

}
