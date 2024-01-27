// CorsConfig.java
package com.boot.Maps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins, headers, and methods
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Allow credentials (if needed)
        config.setAllowCredentials(true);

        // Add mapping for all endpoints
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Specify the endpoint(s) you want to allow CORS for
                        .allowedOrigins("http://localhost:8080") // Add the origins you want to allow (e.g., your frontend URL)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify the HTTP methods you want to allow
                        .allowCredentials(true); // Specify if credentials (such as cookies) are allowed to be sent along with the requests
            }
        };
    }
}
