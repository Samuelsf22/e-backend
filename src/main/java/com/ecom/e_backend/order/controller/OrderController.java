package com.ecom.e_backend.order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecom.e_backend.order.controller.dto.OrderDto;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/order")

public class OrderController {

    @GetMapping("/{orderId}")
    Mono<OrderDto> getOrder(@PathVariable("orderId") Long id) {
        return Mono.just(new OrderDto(id, null, "test", "status"));
    }

    @PostMapping("/")
    Mono<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return Mono.just(orderDto);
    }

    @PutMapping("/{orderId}")
    Mono<OrderDto> updateOrder(@PathVariable("orderId") Long id, @RequestBody OrderDto orderDto) {
        return Mono.just(orderDto);
    }

    @DeleteMapping("/{orderId}")
    Mono<Void> deleteOrder(@PathVariable("orderId") Long id) {
        return Mono.empty();
    }

}
