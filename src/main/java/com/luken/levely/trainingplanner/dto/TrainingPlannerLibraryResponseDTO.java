package com.luken.levely.trainingplanner.dto;

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
