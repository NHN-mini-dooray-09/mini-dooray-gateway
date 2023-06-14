package com.nhnacademy.minidooray.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.ofSeconds(3))
                .setConnectTimeout(Duration.ofSeconds(3))
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/accounts/signup").setViewName("signup");
        registry.addRedirectViewController("/redirect-index", "/");
    }
}
