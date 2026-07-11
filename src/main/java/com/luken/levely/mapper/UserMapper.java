package com.luken.levely.mapper;

import com.luken.levely.dto.request.RegisterUserRequestDTO;
import com.luken.levely.dto.response.LoginUserResponseDTO;
import com.luken.levely.dto.response.RegisterUserResponseDTO;
import com.luken.levely.model.User;
import com.luken.levely.security.config.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterUserResponseDTO toRegisterDTO(User user);

    default User toEntity (RegisterUserRequestDTO body, String encodedPassword) {
        return User.create(
                body.firstName(),
                body.lastName(),
                body.username(),
                body.email(),
                encodedPassword,
                body.birth(),
                body.gender()
        );
    }

}
