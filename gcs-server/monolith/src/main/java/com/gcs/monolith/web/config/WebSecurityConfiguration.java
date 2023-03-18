package com.gcs.monolith.web.config;

import com.gcs.domain.user.constants.Role;
import com.gcs.monolith.web.user.security.JwtAuthenticationProvider;
import com.gcs.supporter.domain.security.EntryPointUnauthorizedHandler;
import com.gcs.supporter.domain.security.JwtAccessDeniedHandler;
import com.gcs.supporter.domain.security.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler(){
        return new JwtAccessDeniedHandler();
    }

    @Bean
    public EntryPointUnauthorizedHandler unauthorizedHandler(){
        return new EntryPointUnauthorizedHandler();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder builder, JwtAuthenticationProvider authenticationProvider) {
        builder.authenticationProvider(authenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

//        configuration.addAllowedOrigin("http://localhost:8080");
//        configuration.addAllowedOrigin("http://127.0.0.1:8080");
//        configuration.addAllowedOrigin("http://localhost:3000");
//        configuration.addAllowedOrigin("http://127.0.0.1:3000");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(
                "/**",
                configuration
        );
        return source;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .disable()
                .cors()
                .and()
                .csrf()
                .disable()
                .headers()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler())
                .authenticationEntryPoint(unauthorizedHandler())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers("/api/user/login").permitAll()
                .antMatchers("/api/users/username").permitAll()
                .antMatchers("/api/user").authenticated()
                .antMatchers("/api/context/current").authenticated()
                .antMatchers(HttpMethod.POST, "/api/context/**" ).authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .disable();
        http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
