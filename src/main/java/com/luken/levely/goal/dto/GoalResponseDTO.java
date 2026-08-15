package com.luken.levely.goal.dto;

import com.luken.levely.goal.enums.GoalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record GoalResponseDTO(
        UUID id,
        Double startWeight,
        Double targetWeight,
        LocalDate deadline,
        GoalStatus goalStatus,
        LocalDate completedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID userId,
        UUID workoutId
) {
}
