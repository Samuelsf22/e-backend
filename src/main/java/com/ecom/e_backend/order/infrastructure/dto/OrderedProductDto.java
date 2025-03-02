package com.ecom.e_backend.order.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.order.domain.models.OrderedProduct;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderedProductDto(

    @NotNull(message = "Product public id is required")
    @JsonProperty("product_public_id")
    UUID productPublicId,

    @NotNull(message = "Price is required")
    Double price,

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
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

    public static OrderedProductDto from(OrderedProduct orderedProduct) {
        return OrderedProductDto.builder()
                .productPublicId(orderedProduct.getProductPublicId())
                .price(orderedProduct.getPrice())
                .quantity(orderedProduct.getQuantity())
                .productName(orderedProduct.getProductName())
                .build();
    }
    
}
