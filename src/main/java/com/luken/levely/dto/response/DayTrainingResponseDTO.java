package com.luken.levely.dto.response;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.UUID;

public record DayTrainingResponseDTO(
        UUID id,
        String name,
        String notes,
        DayOfWeek dayOfWeek,
        int quantityWorkout,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID trainingPlannerId
) {
}