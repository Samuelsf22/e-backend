package com.ecom.e_backend.order.infrastructure.handler;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.order.domain.service.OrderService;
import com.ecom.e_backend.order.infrastructure.dto.OrderDetailResponseDto;
import com.ecom.e_backend.order.infrastructure.dto.OrderRequestDto;
import com.ecom.e_backend.order.infrastructure.dto.OrderResponseDto;
import com.ecom.e_backend.user.domain.UserService;
import com.ecom.e_backend.validation.ObjectValidator;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;
    private final UserService userService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> createOrder(ServerRequest request) {
        return request.bodyToMono(OrderRequestDto.class).doOnNext(objectValidator::validate)
                .flatMap(orderRequest -> userService.findByUsername(orderRequest.username())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND , "User not found")))
                        .flatMap(user -> orderService.createOrder(user.getPublicId(), orderRequest.getOrderedProduct()))
                        .flatMap(order -> ServerResponse.ok().bodyValue("Order created successfully")));
    }

    public Mono<ServerResponse> getOrderByUsername(ServerRequest request) {
        String username = request.queryParam("username").get();
        return userService.findByUsername(username)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(orderService.findAllByUserPublicId(user.getPublicId()).map(OrderResponseDto::from),
                                OrderResponseDto.class))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND , "User not found")));
    }

    public Mono<ServerResponse> getOrderDetails(ServerRequest request) {
        UUID orderPublicId = UUID.fromString(request.queryParam("order_public_id").get());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.findAllOrderedProductsByOrderPublicId(orderPublicId)
                        .map(OrderDetailResponseDto::from), OrderDetailResponseDto.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> getAllOrders(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.findAll().map(OrderResponseDto::from), OrderResponseDto.class);
    }

    public Mono<ServerResponse> markOrderAsPaid(ServerRequest request) {
        UUID orderPublicId = UUID.fromString(request.queryParam("order_public_id").get());
        return orderService.updateStatusByPublicId(orderPublicId)
                .flatMap(orderedProduct -> ServerResponse.ok().bodyValue("Order paid successfully"));
    }
}
