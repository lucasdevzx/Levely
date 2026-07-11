package com.luken.levely.service;

import com.luken.levely.dto.request.LoginUserRequestDTO;
import com.luken.levely.dto.request.RegisterUserRequestDTO;
import com.luken.levely.model.User;
import com.luken.levely.security.auth.UserDetailsImpl;
import com.luken.levely.security.config.SecurityConfig;
import com.luken.levely.security.config.TokenConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final SecurityConfig securityConfig;

    public User registerUser(RegisterUserRequestDTO body) {
        String encodedPassword = securityConfig.passwordEncoder().encode(body.password());
        return userService.registerUser(body, encodedPassword);
    }

    public String loginUser(LoginUserRequestDTO body) {
            UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                    body.email(),
                    body.password()
            );

            Authentication authentication = authenticationManager.authenticate(credentials);

            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            User user = principal != null ? principal.getUser() : null;

            if (user == null) {
                throw new NullPointerException("User null");
            }

            return tokenConfig.generateToken(user);
    }
}
