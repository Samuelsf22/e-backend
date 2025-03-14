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

                .GET(PATH + "/details", request -> request.queryParam("order_public_id")
                        .map(id -> handler.getOrderDetails(request))
                        .orElse(ServerResponse.badRequest().bodyValue("Order public id is required")))

                .GET(PATH + "/user", request -> request.queryParam("username")
                        .map(id -> handler.getOrderByUsername(request))
                        .orElse(ServerResponse.badRequest().bodyValue("User public id is required")))

                .GET(PATH, handler::getAllOrders)

                .PUT(PATH + "/payment", request -> request.queryParam("order_public_id")
                        .map(id -> handler.markOrderAsPaid(request))
                        .orElse(ServerResponse.badRequest().bodyValue("Order public id is required")))

                .build();
    }
    
}
