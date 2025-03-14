package com.ecom.e_backend.product.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.infrastructure.handler.CategoryHandler;

@Configuration
public class CategoryRouter {

    private static final String PATH = "/api/category";

    @Bean
    RouterFunction<ServerResponse> categoryRoute(CategoryHandler handler) {
        return RouterFunctions.route()
                .POST(PATH, handler::save)

                .GET(PATH, request -> request.queryParam("public_id")
                        .map(id -> handler.findByPublicId(request))
                        .orElseGet(() -> handler.findAll(request)))

                .DELETE(PATH, request -> request.queryParam("public_id")
                        .map(id -> handler.delete(request))
                        .orElse(ServerResponse.badRequest().bodyValue("Category public id is required")))

                .build();
    }

}
