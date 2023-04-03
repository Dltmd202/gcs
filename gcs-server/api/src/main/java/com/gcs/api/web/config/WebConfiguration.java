package com.gcs.api.web.config;

import com.gcs.api.constants.JwtTokenConstants;
import com.gcs.supporter.domain.security.Jwt;
import com.gcs.supporter.error.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {
    private final JwtTokenConstants jwtTokenConstants;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);
        registry.setOrder(1);
    }

    @Bean
    public Jwt jwt(){
        return new Jwt(jwtTokenConstants.getIssuer(), jwtTokenConstants.getClientSecret(), jwtTokenConstants.getExpirySeconds());
    }

    @Bean
    public ResponseEntityExceptionHandler exceptionHandler(){
        return new GlobalExceptionHandler();
    }

}
