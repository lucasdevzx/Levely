package com.luken.levely.security.auth;

import com.luken.levely.security.config.AuthConfig;
import com.luken.levely.security.config.SecurityConfig;
import com.luken.levely.security.config.TokenConfig;
import com.luken.levely.security.jwt.JwtUserData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;
    private final AuthConfig authConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization != null && headerAuthorization.toLowerCase().startsWith("bearer")) {

            String token = headerAuthorization.substring(7);
            JwtUserData tokenDecoded = tokenConfig.validateToken(token);

            if (tokenDecoded != null) {

                var user = authConfig.loadUserByUsername(tokenDecoded.email());

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}