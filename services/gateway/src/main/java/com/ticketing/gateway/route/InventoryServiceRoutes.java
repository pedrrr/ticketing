package com.ticketing.gateway.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class InventoryServiceRoutes {

    @Value("${inventory.base.url}")
    String baseUrl;

    @Bean(name = "inventoryServiceRouter")
    public RouterFunction<ServerResponse> inventoryServiceRoutes() {

        return route("inventory-service")
                .GET("/api/v1/inventory/events/{eventId}", request ->
                        forwardWithPathVariable(request, "eventId", baseUrl + "/events/")
                )
                .GET("/api/v1/inventory/venues/{venueId}", request ->
                        forwardWithPathVariable(request, "venueId", baseUrl + "/venues/")
                )
                .build();
    }

    private static ServerResponse forwardWithPathVariable(ServerRequest request,
                                                          String pathVariableName,
                                                          String baseUrl) throws Exception {
        String pathVariableValue = request.pathVariable(pathVariableName);
        return HandlerFunctions.http(baseUrl + pathVariableValue).handle(request);
    }

}
