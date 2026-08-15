package com.luken.levely.progress.dto;

public record ProgressTrainingResponseDTO(
        Integer recommendedQuantitySets,
        Double recommendedWeight,
        Integer recommendedReps
) {
}
