package com.ecom.e_backend.order.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.order.infrastructure.handler.OrderHandler;

@Configuration
public class OrderRouter {

    private static final String PATH = "/api/order";

    @Bean
    RouterFunction<ServerResponse> orderRoute(OrderHandler handler) {
        return RouterFunctions.route()
                .POST(PATH, handler::createOrder)
                .build();
    }
    
}
