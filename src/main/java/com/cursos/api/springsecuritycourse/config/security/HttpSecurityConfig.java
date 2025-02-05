package com.cursos.api.springsecuritycourse.config.security;

import com.cursos.api.springsecuritycourse.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // activa y configura componentes como AuthenticationConfiguration
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // deshabilitar protección de cross-site request forgery
        SecurityFilterChain filterChain = http.csrf(csrfCfg -> csrfCfg.disable())
            // Indicar que tipo de sesiones se va manejar, en este caso va ser stateless
            .sessionManagement(sessionCfg -> sessionCfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            // defines el filtro personalizado y como segundo parametro defines el filtro que se va ejecutar después
            // de este filtro personalizado PD: copilot kgada me confunde con sus mensajes recomendados
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authReqConfig -> {
                authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
                authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate").permitAll();

                // todos requieren autenticación excepto los dos de arriba
                authReqConfig.anyRequest().authenticated();
            })
            .build();

        return filterChain;
    }
}
