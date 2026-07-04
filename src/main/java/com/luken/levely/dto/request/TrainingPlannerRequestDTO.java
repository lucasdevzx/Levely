package com.luken.levely.dto.request;

import com.luken.levely.enums.GoalType;

import java.time.LocalDate;

public record TrainingPlannerRequestDTO(
        String name,
        GoalType goalType,
        LocalDate startDate,
        LocalDate endDate
) {
}