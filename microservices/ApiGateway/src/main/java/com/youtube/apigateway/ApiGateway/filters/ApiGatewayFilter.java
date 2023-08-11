package com.youtube.apigateway.ApiGateway.filters;

import com.youtube.apigateway.ApiGateway.exceptions.UnauthrizedException;
import com.youtube.apigateway.ApiGateway.services.AuthService;
import com.youtube.apigateway.ApiGateway.validator.RouteValidator;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayFilter extends AbstractGatewayFilterFactory<ApiGatewayFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private AuthService authService;

    public ApiGatewayFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(ApiGatewayFilter.Config config) {
        return ((exchange, chain) -> {

            String token = null;

            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new UnauthrizedException("missing authorization header");
                }

                String authHeader = exchange
                        .getRequest()
                        .getHeaders()
                        .get(HttpHeaders.AUTHORIZATION)
                        .get(0);

                if (authHeader != null || authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                }

                try {
                    authService.validateToken(token);
                } catch (Exception ex) {
                    throw new UnauthrizedException("Invalid access");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {
        // Configuration properties for the filter (if any)
    }
}
