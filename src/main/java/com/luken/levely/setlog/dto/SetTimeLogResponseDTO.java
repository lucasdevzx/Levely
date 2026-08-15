package com.luken.levely.setlog.dto;

import java.util.UUID;

public record SetTimeLogResponseDTO(
        UUID id,
        Integer orderIndex,
        UUID dayTrainingWorkoutLogId,
        Integer durationSeconds
) {
}
