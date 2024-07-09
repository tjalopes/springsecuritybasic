package com.example.springsecuritybasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean("restTemplate")  // This names the bean explicitly
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
