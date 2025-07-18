package org.enaaskills.apigateway.config;

import org.enaaskills.apigateway.filter.JwtAuthenticationGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

    @Bean
    public JwtAuthenticationGatewayFilterFactory jwtAuthenticationGatewayFilterFactory(WebClient.Builder webClientBuilder) {
        return new JwtAuthenticationGatewayFilterFactory(webClientBuilder);
    }
}