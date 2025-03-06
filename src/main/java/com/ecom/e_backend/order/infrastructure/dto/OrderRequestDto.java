package com.ecom.e_backend.order.infrastructure.dto;

import java.util.List;

import com.ecom.e_backend.order.domain.models.OrderedProduct;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import reactor.core.publisher.Flux;

@Builder
public record OrderRequestDto(

    @NotNull(message = "User id is required")
    String username,

    @NotEmpty(message = "At least one product is required")
    @Valid
    List<OrderedProductDto> products

) {

    public Flux<OrderedProduct> getOrderedProduct(){
        return Flux.fromIterable(products).map(dto -> dto.toOrderedProduct());
    }
    
}
