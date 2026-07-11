package com.luken.levely.dto.request;

public record LoginUserRequestDTO(
        String email,
        String password
) {
}