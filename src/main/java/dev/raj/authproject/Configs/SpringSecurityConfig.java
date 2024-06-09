package dev.raj.authproject.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
// its allowing spring security to allow all requests and disable csrf
    //csrf is a security feature that prevents cross-site request forgery attacks
    // by default spring
    //In the context of your Spring Boot application, if you have Spring Security enabled, it will by default add authentication to all HTTP endpoint
    // but we want only bcrupt functionality from spring security for now
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

}
// one service shouldnot directly call other service db
