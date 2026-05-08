package com.efootball.tournament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowedOrigins(
                            List.of("http://localhost:5173",
                                    "https://tournament-manager-hm8d.onrender.com"
                            ));


                    config.setAllowedMethods(
                            List.of("GET", "POST", "PUT",
                                    "DELETE", "OPTIONS"));

                    config.setAllowedHeaders(List.of("*"));

                    config.setAllowCredentials(true);

                    return config;
                }))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers(
                                "/api/users/login",
                                "/api/users/register"
                        ).permitAll()

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        .requestMatchers(
                                "/api/tournaments/**"
                        ).permitAll()

                        .requestMatchers(
                                "/api/teams/**"
                        ).permitAll()

                        .requestMatchers(
                                "/api/matches/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                );

        return http.build();
    }
}