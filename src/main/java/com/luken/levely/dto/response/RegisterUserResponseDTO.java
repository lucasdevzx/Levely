package com.luken.levely.dto.response;

import java.time.LocalDate;

public record RegisterUserResponseDTO(
        String firstName,
        String lastName,
        String username
) {
}