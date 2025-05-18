package com.example.WebProject2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // tüm endpoint'ler için geçerli
                .allowedOrigins("http://localhost:5173") // Vue.js frontend URL
                .allowedMethods("*") // GET, POST, PUT, DELETE...
                .allowedHeaders("*");
    }
}
