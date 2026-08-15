package com.luken.levely.progress.dto;

import com.luken.levely.progress.enums.ProgressTrainingType;

public record ProgressTrainingRequestDTO(
        ProgressTrainingType progressTrainingType
) {
}
