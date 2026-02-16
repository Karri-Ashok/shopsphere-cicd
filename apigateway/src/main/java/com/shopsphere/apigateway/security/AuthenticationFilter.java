package com.shopsphere.apigateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            // ✅ Allow CORS preflight requests
            if (exchange.getRequest().getMethod().name().equals("OPTIONS")) {
                return chain.filter(exchange);
            }

            // ✅ Check Authorization header exists
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Missing Authorization Header"
                );
            }

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid Authorization Header"
                );
            }

            String token = authHeader.substring(7);

            // ✅ Validate token
            if (!jwtUtil.validateToken(token)) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid JWT Token"
                );
            }

            // ✅ Extract email
            String email = jwtUtil.extractUsername(token);

            // ✅ FORWARD STANDARDIZED HEADER
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(builder ->
                            builder.header("X-USER-EMAIL", email)
                    )
                    .build();

            return chain.filter(modifiedExchange);
        };
    }

    public static class Config {}
}
