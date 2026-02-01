package com.retail.cart.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${catalog.service.url}")
    private String catalogBaseUrl;

    @Bean
    public WebClient getCatalogWebClient() {
        return WebClient.builder()
                .baseUrl(catalogBaseUrl)
                .build();
    }
}