package com.luken.levely.user.dto;

import com.luken.levely.user.enums.Gender;

import java.time.LocalDate;

public record RegisterUserRequestDTO(
        String firstName,
        String lastName,
        String username,
        String email,
        String password,
        LocalDate birth,
        Gender gender
) {
}