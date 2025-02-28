package com.microservices.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return GatewayRouterFunctions.route("product-service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    /*By default, Spring Cloud Gateway preserves the matched path when forwarding requests.

The request comes to the Gateway at:
http://gateway-server/api/product
It gets forwarded to:
http://localhost:8080/api/product
Why?
Even though you only specified "http://localhost:8080", the original request path /api/product is automatically preserved.
Spring Cloud Gateway appends the matched path to the base URL.
3️⃣ What Happens Internally
The default behavior in Spring Cloud Gateway is:

Base URL (http://localhost:8080) is resolved first.
The matched request path (/api/product) is automatically appended.
The final destination URL becomes:
http://localhost:8080/api/product*/

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return GatewayRouterFunctions.route("order-service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return GatewayRouterFunctions.route("product-service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }
}
