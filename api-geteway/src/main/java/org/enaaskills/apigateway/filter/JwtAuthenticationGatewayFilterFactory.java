package org.enaaskills.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JwtAuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config> {

    private final WebClient.Builder webClientBuilder;

    public JwtAuthenticationGatewayFilterFactory(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing Authorization Header");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new RuntimeException("Incorrect Authorization Structure");
            }

            return webClientBuilder.build()
                    .get()
                    .uri("http://auth-service/auth/validate?token=" + parts[1])
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .map(isValid -> {
                        if (isValid) {
                            return exchange;
                        } else {
                            throw new RuntimeException("Invalid Token");
                        }
                    }).flatMap(chain::filter);
        });
    }

    public static class Config {
        // Put the configuration properties
    }
}
