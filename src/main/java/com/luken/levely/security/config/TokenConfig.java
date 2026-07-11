package com.luken.levely.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.luken.levely.model.User;
import com.luken.levely.security.auth.UserDetailsImpl;
import com.luken.levely.security.jwt.JwtUserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class TokenConfig {

    // Change after
    private final String secret = "Lucas";

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("Levely")
                .withSubject(user.getEmail())
                .withExpiresAt(expirationAt())
                .sign(Algorithm.HMAC256(secret));
    }

    public JwtUserData validateToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("Levely")
                    .build()
                    .verify(token);

            String email = decodedJWT.getSubject();
            return new JwtUserData(email);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public Instant expirationAt() {
        return Instant.now().plus(600, ChronoUnit.SECONDS); // 10 Minutes
    }
}
