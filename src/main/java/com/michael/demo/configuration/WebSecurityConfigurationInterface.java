package com.michael.demo.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface WebSecurityConfigurationInterface {
    void configure(HttpSecurity http) throws Exception;
}
