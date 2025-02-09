package com.ecom.e_backend.security.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.security.handler.AuthHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AuthRouter {

    private static final String PATH = "auth/";

    @Bean
    RouterFunction<ServerResponse> authRoute(AuthHandler authHandler) {
        return RouterFunctions.route()
                .POST(PATH + "login", authHandler::login)
                .POST(PATH + "create", authHandler::create)
                .build();
    }

}
