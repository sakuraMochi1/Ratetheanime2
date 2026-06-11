package com.duoc.ratetheanime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient malWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.myanimelist.net/v2")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
