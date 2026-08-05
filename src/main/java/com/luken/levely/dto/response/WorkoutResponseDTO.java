package com.luken.levely.dto.response;

import java.util.UUID;

public record WorkoutResponseDTO(
        UUID id,
        String name,
        String description,
        Double recommendedWeightIncrement,
        Integer orderIndex
) {
}