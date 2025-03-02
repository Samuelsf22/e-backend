package com.ecom.e_backend.order.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.order.domain.models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record OrderResponseDto(

    @JsonProperty("order_public_id")
    UUID publicId,

    String status

) {

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
                .publicId(order.getPublicId())
                .status(order.getStatus().name())
                .build();
    }
}
