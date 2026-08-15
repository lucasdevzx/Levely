package com.luken.levely.user;

import com.luken.levely.user.dto.RegisterUserRequestDTO;
import com.luken.levely.user.dto.RegisterUserResponseDTO;
import org.mapstruct.Mapper;

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
