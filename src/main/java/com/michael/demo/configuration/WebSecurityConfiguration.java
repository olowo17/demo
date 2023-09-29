package com.michael.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/users/**", "/address","/login","/validate-token").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement(session->session(session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) );

        return http.build();
    }

    private void session(SessionManagementConfigurer<HttpSecurity> httpSecuritySessionManagementConfigurer) {
    }
}
