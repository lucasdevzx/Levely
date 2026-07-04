package com.luken.levely.dto.response;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.UUID;

public record DayTrainingResponseDTO(
        String name,
        String notes,
        DayOfWeek dayOfWeek,
        Integer weekNumber,
        Integer quantityWorkout,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID trainingPlannerId
) {
}