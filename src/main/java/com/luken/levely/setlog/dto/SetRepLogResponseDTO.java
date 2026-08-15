package com.luken.levely.setlog.dto;

import java.util.UUID;

public record SetRepLogResponseDTO(
        UUID id,
        Integer orderIndex,
        UUID dayTrainingWorkoutLogId,
        Integer reps,
        Double weight
) {
}