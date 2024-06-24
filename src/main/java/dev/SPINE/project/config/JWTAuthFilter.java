package dev.SPINE.project.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Check JWT token exists. Checks for the "Bearer {JWT_Token} pattern as well
        final String authHeader = request.getHeader("Authorization");
        final String userEmail;
        final String jwt_token;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt_token = authHeader.substring(7);



    }
}
