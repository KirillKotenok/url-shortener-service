package com.kornello.url_shortener_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("objectMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
