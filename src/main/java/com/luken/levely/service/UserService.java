package com.luken.levely.service;

import com.luken.levely.dto.request.RegisterUserRequestDTO;
import com.luken.levely.mapper.UserMapper;
import com.luken.levely.model.User;
import com.luken.levely.repository.UserRepository;
import com.luken.levely.security.config.SecurityConfig;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity user not found by email: " + email)));
    }

    public User registerUser(RegisterUserRequestDTO body, String encodedPassword) {
        var user = userMapper.toEntity(body, encodedPassword);
        return userRepository.save(user);
    }
}
