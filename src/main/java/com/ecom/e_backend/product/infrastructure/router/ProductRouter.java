package com.ecom.e_backend.product.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.infrastructure.handler.ProductHandler;

@Configuration
public class ProductRouter {

    private static final String PATH = "/api/product";

    @Bean
    RouterFunction<ServerResponse> ProductRoute(ProductHandler handler) {
        return RouterFunctions.route()
                .POST(PATH, handler::save)

                .GET(PATH, request -> request.queryParam("public_id")
                        .map(id -> handler.findByPublicId(request))
                        .orElseGet(() -> handler.findAll(request)))

                .GET(PATH + "/category", request -> request.queryParam("category_public_id")
                        .map(id -> handler.findByCategoryPublicId(request))
                        .orElse(ServerResponse.badRequest().bodyValue("category_public_id is required")))

                .GET(PATH + "/featured", handler::findAllByFeatured)

                .DELETE(PATH, request -> request.queryParam("public_id")
                        .map(id -> handler.deleteByPublicId(request))
                        .orElse(ServerResponse.badRequest().bodyValue("public_id is required")))

                .PUT(PATH, request -> request.queryParam("public_id")
                        .map(publicId -> request.queryParam("quantity")
                                .map(quantity -> handler.updateQuantity(request))
                                .orElse(ServerResponse.badRequest().bodyValue("quantity is required")))
                        .orElse(ServerResponse.badRequest().bodyValue("public_id is required")))

                .build();
    }
}
