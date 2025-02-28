package com.ecom.e_backend.order.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.order.domain.models.OrderedProduct;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrderedProductDto(

    @NotBlank(message = "Product public id is required")
    @JsonProperty("product_id")
    UUID productPublicId,

    @NotBlank(message = "Price is required")
    Double price,

    @NotBlank(message = "Quantity is required")
    @Size(min = 1, message = "Quantity must be greater than 0")
    Integer quantity,

    @NotBlank(message = "Product name is required")
    @JsonProperty("product_name")
    String productName
) {

    public OrderedProduct toOrderedProduct() {
        return OrderedProduct.builder()
                .productPublicId(productPublicId)
                .price(price)
                .quantity(quantity)
                .productName(productName)
                .build();
    }
    
}
