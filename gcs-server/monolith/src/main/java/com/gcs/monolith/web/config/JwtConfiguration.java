package com.gcs.monolith.web.config;

import com.gcs.monolith.constants.JwtTokenConstants;
import com.gcs.monolith.domain.user.service.UserService;
import com.gcs.monolith.web.user.security.JwtAuthenticationProvider;
import com.gcs.supporter.domain.security.Jwt;
import com.gcs.supporter.domain.security.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {
    private final Jwt jwt;
    private final JwtTokenConstants jwtTokenConstants;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter(jwtTokenConstants.getHeader(), jwt);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(UserService userService) {
        return new JwtAuthenticationProvider(userService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
