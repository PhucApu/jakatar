package com.bus_station_ticket.project.ProjectConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

       @Bean
       public CorsFilter corsFilter() {
              UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
              CorsConfiguration config = new CorsConfiguration();

              // Cho phép các origins cụ thể
              config.addAllowedOrigin("http://localhost:3000");
              config.addAllowedOrigin("http://localhost:5173");
              // Nếu cần thêm origins khác:
              // config.addAllowedOrigin("https://your-production-domain.com");

              // Cho phép credentials (cookies, authorization headers)
              config.setAllowCredentials(true);

              // Cho phép các HTTP methods cụ thể
              config.addAllowedMethod("GET");
              config.addAllowedMethod("POST");
              config.addAllowedMethod("PUT");
              config.addAllowedMethod("DELETE");
              config.addAllowedMethod("OPTIONS");

              // Cho phép các headers cụ thể
              config.addAllowedHeader("Origin");
              config.addAllowedHeader("Content-Type");
              config.addAllowedHeader("Accept");
              config.addAllowedHeader("Authorization");

              // Thời gian cache cho preflight requests (3600 giây = 1 giờ)
              config.setMaxAge(3600L);

              source.registerCorsConfiguration("/**", config);
              return new CorsFilter(source);
       }
}