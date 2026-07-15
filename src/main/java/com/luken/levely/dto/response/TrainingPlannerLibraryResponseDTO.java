package com.luken.levely.dto.response;

import com.luken.levely.model.TrainingPlanner;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrainingPlannerLibraryResponseDTO(
        UUID id,
        UUID trainingPlannerId,
        UUID libraryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
