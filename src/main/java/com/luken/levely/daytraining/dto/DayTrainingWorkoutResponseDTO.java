package com.luken.levely.daytraining.dto;

import java.util.UUID;

public record DayTrainingWorkoutResponseDTO(
        UUID id,
        UUID dayTrainingId,
        UUID workoutId,
        Integer orderIndex
) {
}
