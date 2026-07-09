package com.luken.levely.dto.response;

import java.util.UUID;

public record SetRepLogResponseDTO(
        UUID id,
        Integer orderIndex,
        UUID dayTrainingWorkoutLogId,
        Integer reps,
        Double weight
) {
}