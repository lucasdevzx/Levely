package com.luken.levely.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record DayTrainingWorkoutLogResponseDTO(
        UUID id,
        UUID dayTrainingId,
        UUID workoutId,
        Integer orderIndex,
        boolean completed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
