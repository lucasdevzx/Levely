package com.luken.levely.dto.response;

import java.util.UUID;

public record WorkoutResponseDTO(
        UUID id,
        String name,
        String description,
        Integer orderIndex
) {
}