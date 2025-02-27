package com.ecom.e_backend.user.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ecom.e_backend.user.infrastructure.handler.UserHandler;

@Configuration
public class UserRouter {

    private static final String PATH = "/api/user";

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserHandler userHandler) {
        return RouterFunctions.route()
                .GET(PATH, request -> request.queryParam("public_id")
                        .map(id -> userHandler.findUserById(request))
                        .orElseGet(() -> userHandler.findAllUsers(request)))

                .DELETE(PATH, request -> request.queryParam("public_id")
                        .map(id -> userHandler.deleteUser(request))
                        .orElse(ServerResponse.badRequest().bodyValue("Public id is required")))

                .build();
    }
}
