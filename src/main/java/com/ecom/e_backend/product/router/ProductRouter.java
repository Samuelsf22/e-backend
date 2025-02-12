package com.ecom.e_backend.product.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.product.handler.ProductHandler;

@Configuration
public class ProductRouter {

    private final String PATH = "/api/product";
    
    @Bean
    RouterFunction<ServerResponse> router(ProductHandler handler) {
        return RouterFunctions.route()
                .POST(PATH, handler::save)
                .GET(PATH, handler::findAll)
                .DELETE(PATH + "/{public_id}", handler::deleteByPublicId)
                .GET(PATH + "/{public_id}", handler::findByPublicId)
                .build();
    }
}
