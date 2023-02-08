package com.makowski.fuhrpark.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.makowski.fuhrpark.security.filter.AuthenticationFilter;
import com.makowski.fuhrpark.security.filter.ExceptionHandlerFilter;
import com.makowski.fuhrpark.security.filter.JWTAuthorizationFilter;
import com.makowski.fuhrpark.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@AllArgsConstructor
public class SecurityConfig {

    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http        
            .headers().frameOptions().disable() 
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/h2/**").permitAll() 
            .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(new AuthenticationFilter(customAuthenticationManager))
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
    
}