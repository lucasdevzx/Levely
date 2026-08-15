package com.luken.levely.trainingplanner.dto;

import com.luken.levely.trainingplanner.enums.PlannerStatus;

public record TrainingPlannerStatusRequestDTO(
        PlannerStatus plannerStatus) {
}