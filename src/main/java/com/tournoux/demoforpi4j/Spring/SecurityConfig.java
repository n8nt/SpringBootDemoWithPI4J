package com.tournoux.demoforpi4j.Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



//    private static final String[] AUTH_WHITELIST = {
//            // -- Swagger UI v2
//            "/v2/api-docs",
//            "v2/api-docs",
//            "/swagger-resources",
//            "swagger-resources",
//            "/swagger-resources/**",
//            "swagger-resources/**",
//            "/configuration/ui",
//            "configuration/ui",
//            "/configuration/security",
//            "configuration/security",
//            "/swagger-ui.html",
//            "swagger-ui.html",
//            "webjars/**",
//            // -- Swagger UI v3
//            "/v3/api-docs/**",
//            "v3/api-docs/**",
//            "/swagger-ui/**",
//            "swagger-ui/**",
//            // CSA Controllers
//            "/csa/api/token",
//            // Actuators
//            "/actuator/**",
//            "/health/**"
//    };
//
//    private static final String [] AUTH_WHITE_LIST2 = {
//
//
//        "/",
//        "/checkMe",
//        "/blinkLeds/**"
//
//
//    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests( auth -> auth
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(withDefaults());
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( new AntPathRequestMatcher("swagger-ui/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/blinkLeds/**")).permitAll()
                        .requestMatchers( new AntPathRequestMatcher("/checkMe")).permitAll()
                        .anyRequest().authenticated())
                .httpBasic();

        return httpSecurity.build();
    }
}
