package com.luken.levely.user;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.user.dto.RegisterUserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity user not found by email: " + email), ApiError.RESOURCE_NOT_FOUND));
    }

    public User registerUser(RegisterUserRequestDTO body, String encodedPassword) {
        var user = userMapper.toEntity(body, encodedPassword);
        return userRepository.save(user);
    }
}
