package com.duoc.ratetheanime.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro JWT que se ejecuta una vez por cada request HTTP.
 * Extrae el token, limpia el formato de los roles y da acceso al contexto.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);

                // Limpieza de corchetes [ ] o comillas que suelen meter las librerías JWT
                if (role != null) {
                    role = role.replace("[", "").replace("]", "").replace("\"", "").trim();
                }

                System.out.println("Filtro procesando - Usuario: " + username + " -> Autoridad limpia inyectada: " + role);

                // Construye el objeto de autenticación con la autoridad limpia
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        List.of(new SimpleGrantedAuthority(role))
                );

                // Registra la autenticación en el contexto de seguridad de este hilo
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Continúa con el siguiente filtro de la cadena
        filterChain.doFilter(request, response);
    }
}