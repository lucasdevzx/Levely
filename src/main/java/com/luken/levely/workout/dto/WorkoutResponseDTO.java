package com.luken.levely.workout.dto;

import java.util.UUID;

public record  WorkoutResponseDTO(
        UUID id,
        String name,
        String description,
        Double recommendedWeightIncrement,
        Integer orderIndex
) {
}