package com.accenture.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator accentureRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p.path("/accenture/customers/**")
						.filters( f -> f.rewritePath("/accenture/customers/(?<segment>.*)", "/${segment}" )
								.circuitBreaker(config -> config.setName("customersCircuitBreaker")
										.setFallbackUri("forward:/callToSupport")))
						.uri("lb://CUSTOMERS"))

				.route(p -> p.path("/accenture/accounts/**")
						.filters( f -> f.rewritePath("/accenture/accounts/(?<segment>.*)", "/${segment}" )
								.circuitBreaker(config -> config.setName("accountsCircuitBreaker")
										.setFallbackUri("forward:/callToSupport")))
						.uri("lb://ACCOUNTS"))

				.build();
	}
}
