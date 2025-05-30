package com.alten.ecommerce.config;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.security.JwtAuthFilter;
import com.alten.ecommerce.service.JwtUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.headers(headers -> headers.frameOptions().disable())
            .authorizeHttpRequests()
            .requestMatchers("/token", "/account", "/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**", "/api/wishlist").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/products").access(adminOnly())
            .requestMatchers(HttpMethod.PATCH, "/api/products/**").access(adminOnly())
            .requestMatchers(HttpMethod.DELETE, "/api/products/**").access(adminOnly())
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new JwtAuthFilter(jwtUtil, userRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private AuthorizationManager<RequestAuthorizationContext> adminOnly() {
        return (Supplier<Authentication> authentication, RequestAuthorizationContext context) -> {
            Authentication auth = authentication.get();
            if (auth != null && auth.getPrincipal() instanceof String email) {
                return new AuthorizationDecision(email.equals("admin@admin.com"));
            }
            return new AuthorizationDecision(false);
        };
    }
    
}
