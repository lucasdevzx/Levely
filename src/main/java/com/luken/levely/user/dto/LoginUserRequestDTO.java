package com.luken.levely.user.dto;

public record LoginUserRequestDTO(
        String email,
        String password
) {
}