package com.luken.levely.daytraining.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DayTrainingWorkoutLogResponseDTO(
        UUID id,
        UUID dayTrainingId,
        UUID workoutId,
        Integer orderIndex,
        boolean completed,
        LocalDateTime completedAt,
        long timeTraining,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
