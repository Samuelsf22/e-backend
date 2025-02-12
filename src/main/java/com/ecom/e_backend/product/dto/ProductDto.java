package com.ecom.e_backend.product.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import jakarta.validation.constraints.NotNull;

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

    @NotBlank(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    double price,

    @NotNull(message = "Featured is required")
    boolean featured,

    @NotBlank(message = "Stock is required")
    @Min(value = 1, message = "Stock must be greater than 0")
    int stock,

    @NotBlank(message = "Category is required")
    String category

) {}
