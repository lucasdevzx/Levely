package com.luken.levely.trainingplanner.dto;

import com.luken.levely.goal.enums.GoalType;

import java.time.LocalDate;

public record TrainingPlannerRequestDTO(
        String name,
        GoalType goalType,
        LocalDate startDate,
        LocalDate endDate
) {
}