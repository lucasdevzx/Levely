package com.luken.levely.trainingplanner.dto;

import com.luken.levely.goal.enums.GoalType;
import com.luken.levely.trainingplanner.enums.PlannerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TrainingPlannerResponseDTO(

        UUID id,
        String name,
        GoalType goalType,
        PlannerStatus plannerStatus,
        LocalDate startDate,
        LocalDate endDate,
        int totalWeeks,
        int currentWeek,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
