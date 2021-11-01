package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/create/**")
                        .uri("lb://CREATE-SERVICE"))
                .route(p -> p.path("/convert/**")
                        .uri("lb://CONVERT-SERVICE"))
                .route(p -> p.path("/report/**")
                        .uri("lb://REPORTING-SERVICE"))

                .route(p -> p.path("/lead**")
                        .uri("lb://LEAD-SERVICE"))
                .route(p -> p.path("/lead/**")
                        .uri("lb://LEAD-SERVICE"))

                .route(p -> p.path("/accounts/**")
                        .uri("lb://ACCOUNT-SERVICE"))
                .route(p -> p.path("/accounts**")
                        .uri("lb://ACCOUNT-SERVICE"))

                .route(p -> p.path("/contacts/**")
                        .uri("lb://CONTACT-SERVICE"))
                .route(p -> p.path("/contacts**")
                        .uri("lb://CONTACT-SERVICE"))

                .route(p -> p.path("/opps/**")
                        .uri("lb://OPPORTUNITY-SERVICE"))

                .route(p -> p.path("/salesrep**")
                        .uri("lb://SALESREP-SERVICE"))
                .route(p -> p.path("/salesrep/**")
                        .uri("lb://SALESREP-SERVICE"))
                .build();
    }
}
