package com.ahmed.leavemanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ahmed.leavemanagement.security.JwtAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http

                // Disable CSRF because we use JWT
                .csrf(csrf -> csrf.disable())


                // JWT = stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                // Connect our UserDetailsService + BCrypt authentication
                .authenticationProvider(authenticationProvider)

                .addFilterBefore(
                 jwtAuthenticationFilter,
                 UsernamePasswordAuthenticationFilter.class
                 )


                .authorizeHttpRequests(auth -> auth


                        // Public endpoints
                        .requestMatchers("/api/auth/**")
                        .permitAll()


                        // Only ADMIN
                        .requestMatchers("/api/users/**")
                        .hasRole("ADMIN")


                        // Only ADMIN
                        .requestMatchers("/api/departments/**")
                        .hasRole("ADMIN")


                        // Leave requests
                        .requestMatchers("/api/leave-requests/**")
                        .hasAnyRole(
                                "ADMIN",
                                "MANAGER",
                                "EMPLOYEE"
                        )


                        // Everything else needs authentication
                        .anyRequest()
                        .authenticated()
                );


        return http.build();
    }

}