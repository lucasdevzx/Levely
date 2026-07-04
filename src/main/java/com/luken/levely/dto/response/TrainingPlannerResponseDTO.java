package com.luken.levely.dto.response;

import com.luken.levely.enums.GoalType;
import com.luken.levely.enums.PlannerStatus;

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
        Integer totalWeeks,
        Integer currentWeek,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
