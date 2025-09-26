package com.example.gatewayserver.filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter(){
        return new GlobalFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                Mono<Void> filterChainMono = chain.filter(exchange);
                 Mono<Void> postProcessingMono = filterChainMono.then(Mono.fromRunnable(() -> {
                    HttpHeaders httpHeaders = exchange.getResponse().getHeaders();
                     // Extract the correlationId using the utility class
                     String correlationId = filterUtility.getCorrelationId(httpHeaders);

                     // If the response does not already contain the correlation ID, add it
                     if (!exchange.getResponse().getHeaders().containsKey(filterUtility.CORRELATION_ID)) {
                         logger.debug("Updated the correlation id to the outbound headers: {}", correlationId);
                         exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID, correlationId);
                     }
                 }));
                 return postProcessingMono;
            }
        };
    }
}
