package com.eren.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF korumasını devre dışı bırakır
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/eureka/**") // Eureka endpoint'lerine erişime izin verir
                        .permitAll()
                        .anyExchange() // Diğer tüm endpoint'ler için kimlik doğrulama gerektirir
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec
                        .jwt(Customizer.withDefaults()) // JWT tabanlı OAuth2 kaynak sunucusu yapılandırması
                );

        return http.build(); // Güvenlik yapılandırmasını oluşturur
    }
}