package com.luken.levely.dto.response;

public record ProgressTrainingResponseDTO(
        Integer recommendedQuantitySets,
        Double recommendedWeight,
        Integer recommendedReps
) {
}
