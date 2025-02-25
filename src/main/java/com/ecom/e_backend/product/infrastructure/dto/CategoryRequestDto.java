package com.ecom.e_backend.product.infrastructure.dto;

import com.ecom.e_backend.product.domain.models.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequestDto(

    @NotBlank(message = "Category name is required")
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters")
    String name

) {

    public Category toCategory() {
        return Category.builder()
            .name(name())
            .build();
    }
    
}
