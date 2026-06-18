package com.duoc.ratetheanime.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 1. Endpoints públicos (Login, Registro y Swagger)
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()

                // 2. EXCEPCIÓN TOTAL PARA EL ENDPOINT DE USUARIOS
                // Mapeamos de forma explícita con y sin ID tanto para 'usuarios' como 'usuario'
                .requestMatchers("/api/v1/usuarios").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                .requestMatchers("/api/v1/usuarios/*").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                .requestMatchers("/api/v1/usuarios/**").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                
                .requestMatchers("/api/v1/usuario").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                .requestMatchers("/api/v1/usuario/*").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")
                .requestMatchers("/api/v1/usuario/**").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")

                // 3. REGLAS GENERALES PARA EL RESTO DEL SISTEMA (Animes, valoraciones, etc.)
                // Cualquier GET del resto de la app requiere estar logueado (USER o ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyAuthority("USER", "ROLE_USER", "ADMIN", "ROLE_ADMIN")

                // Cualquier POST, PUT o DELETE del resto de la app es estrictamente para ADMIN
                .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")

                // Cualquier otra petición fuera de api/v1 requiere autenticación básica
                .anyRequest().authenticated()
            )
            // Registramos el filtro JWT
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}