package com.example.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

public class FilterUtility {
    public static final String CORRELATION_ID = "X-Correlation-ID";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if(requestHeaders.get(CORRELATION_ID) != null){
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        }else{
            return null;
        }
    }
    public ServerWebExchange setRequestHeader(ServerWebExchange serverWebExchange, String headerName, String headerValue){
        return serverWebExchange.mutate().request(serverWebExchange.getRequest().mutate().header(headerName, headerValue).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId){
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
