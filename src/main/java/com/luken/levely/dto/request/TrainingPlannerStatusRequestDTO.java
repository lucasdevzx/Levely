package com.luken.levely.dto.request;

import com.luken.levely.enums.PlannerStatus;

public record TrainingPlannerStatusRequestDTO(
        PlannerStatus plannerStatus) {
}