package com.ecom.e_backend.auth.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.auth.infrastructure.handler.AuthHandler;

@Configuration
public class AuthRouter {

    private static final String PATH = "api/auth/";

    @Bean
    RouterFunction<ServerResponse> authRoute(AuthHandler authHandler) {
        return RouterFunctions.route()
                .POST(PATH + "login", authHandler::login)
                .POST(PATH + "create", authHandler::create)
                .build();
    }

}
