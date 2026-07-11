package com.luken.levely.dto.request;

import com.luken.levely.enums.Gender;

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