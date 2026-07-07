package com.luken.levely.dto.response;

import com.luken.levely.model.DayTraining;

import java.util.UUID;

public record DayTrainingWorkoutResponseDTO(
        UUID id,
        UUID dayTrainingId,
        UUID workoutId,
        Integer orderIndex

) {
}
