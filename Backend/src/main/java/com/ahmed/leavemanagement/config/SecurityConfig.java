package com.ahmed.leavemanagement.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ahmed.leavemanagement.security.JwtAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http

                // Disable CSRF because we use JWT
                .csrf(csrf -> csrf.disable())


                // JWT is stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                // Authentication provider
                .authenticationProvider(authenticationProvider)


                // JWT filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )


                .authorizeHttpRequests(auth -> auth


                        // Public authentication endpoints
                        .requestMatchers("/api/auth/login")
                        .permitAll()


                        // Register should be protected
                        // Admin creates users
                        .requestMatchers("/api/auth/register")
                        .hasRole("ADMIN")


                        // Everything else requires login
                        .anyRequest()
                        .authenticated()

                );


        return http.build();
    }

}