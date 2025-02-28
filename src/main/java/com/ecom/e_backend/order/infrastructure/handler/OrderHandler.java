package com.ecom.e_backend.order.infrastructure.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.order.domain.service.OrderService;
import com.ecom.e_backend.order.infrastructure.dto.OrderRequestDto;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> createOrder(ServerRequest request) {
        return request.bodyToMono(OrderRequestDto.class).doOnNext(objectValidator::validate)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(orderService.createOrder(dto.userPublicId(), dto.getOrderedProduct()), Void.class));
    }
    
}
