package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	//After defining the routes then no more present a default path which is all microservices name in UpperCase
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
						.route(p -> p.path("/microservices/accounts/**")  //Prediate
								.filters(f -> f.rewritePath("/microservices/accounts/(?<segments>.*)", "/${segments}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())) //filters
								.uri("lb://ACCOUNTS"))
						.route(p -> p.path("/microservices/cards/**")
								.filters(f -> f.rewritePath("/microservices/cards/(?<segments>.*)", "/${segments}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://CARDS"))
						.route(p -> p.path("/microservices/loans/**")
								.filters(f -> f.rewritePath("/microservices/loans/(?<segments>.*)", "/${segments}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://LOANS")).build();
	}
}
