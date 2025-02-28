package com.ecom.e_backend.order.infrastructure.dto;

import java.util.List;
import java.util.UUID;

import com.ecom.e_backend.order.domain.models.OrderedProduct;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import reactor.core.publisher.Flux;

@Builder
public record OrderRequestDto(

    @NotNull(message = "User id is required")
    @JsonProperty("user_public_id")
    UUID userPublicId,

    @NotEmpty(message = "At least one product is required")
    List<OrderedProductDto> products

) {

    public Flux<OrderedProduct> getOrderedProduct(){
        return Flux.fromIterable(products).map(dto -> dto.toOrderedProduct());
    }
    
}
