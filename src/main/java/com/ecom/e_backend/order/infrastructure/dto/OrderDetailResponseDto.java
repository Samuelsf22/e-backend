package com.ecom.e_backend.order.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.order.domain.models.OrderedProduct;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record OrderDetailResponseDto(

    @JsonProperty("product_public_id")
    UUID productPublicId,

    Double price,

    Integer quantity,

    @JsonProperty("product_name")
    String productName

) {

    public static OrderDetailResponseDto from(OrderedProduct orderedProduct) {
        return OrderDetailResponseDto.builder()
                .productPublicId(orderedProduct.getProductPublicId())
                .price(orderedProduct.getPrice())
                .quantity(orderedProduct.getQuantity())
                .productName(orderedProduct.getProductName())
                .build();

    }

}
