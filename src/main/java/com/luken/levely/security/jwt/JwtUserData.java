package com.luken.levely.security.jwt;

import java.util.UUID;

public record JwtUserData(
        UUID id,
        String email
) {
}
