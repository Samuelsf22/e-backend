package com.ecom.e_backend.product.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Builder
public record ProductDto(

    @JsonProperty("public_id")
    UUID publicId,

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    String name,

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    String description,

    @NotBlank(message = "Brand is required")
    String brand,

    @NotBlank(message = "Color is required")
    String color,

    @Min(value = 1, message = "Price must be greater than 0")
    double price,

    @NotNull(message = "Featured is required")
    Boolean featured,

    @Min(value = 1, message = "Stock must be greater than 0")
    int stock,

    @JsonProperty("picture_url")
    @NotNull(message = "Picture URL is required")
    @Pattern(regexp = "^(http|https)://.*$", message = "Picture URL must be a valid URL")
    String pictureUrl,

    @JsonProperty("category_id")
    @Min(value = 1, message = "Category ID must be greater than 0")
    Long categoryId

) {}
