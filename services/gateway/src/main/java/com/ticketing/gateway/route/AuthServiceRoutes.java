package com.ticketing.gateway.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class AuthServiceRoutes {

    @Value("${auth.base.url}")
    private String baseUrl;

    @Bean(name = "authServiceRouter")
    public RouterFunction<ServerResponse> authServiceRoutes() {
        return route("security-service").POST("/api/v1/auth/signup", http())
                .before(uri(baseUrl + "/signup"))
                .build();
    }
}
