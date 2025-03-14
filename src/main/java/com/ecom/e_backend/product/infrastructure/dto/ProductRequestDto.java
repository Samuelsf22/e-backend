package com.ecom.e_backend.product.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.product.domain.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductRequestDto(

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 50, message = "Product name must be between 3 and 50 characters")
    String name,

    @NotBlank(message = "Description is required")
    String description,

    @NotBlank(message = "Brand is required")
    String brand,

    @NotBlank(message = "Color is required")
    String color,

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    double price,

    boolean featured,

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    int stock,

    @JsonProperty("category_public_id")
    @NotNull(message = "Category public id is required")
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
            .categoryId(categoryId)
            .build();
    }
    
}
