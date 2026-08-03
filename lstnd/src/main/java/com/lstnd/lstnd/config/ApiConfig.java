package com.lstnd.lstnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiConfig {
    @Bean
    public RestClient restClientRequest() {
        return RestClient.builder().baseUrl("https://api.spotify.com/v1/").build();
    }

    @Bean
    public RestClient restClientAuth() {
        return RestClient.builder().baseUrl("https://accounts.spotify.com/api/token")
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded").build();
    }
}
