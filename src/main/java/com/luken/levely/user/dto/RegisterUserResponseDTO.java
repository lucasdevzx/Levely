package com.luken.levely.user.dto;

public record RegisterUserResponseDTO(
        String firstName,
        String lastName,
        String username
) {
}