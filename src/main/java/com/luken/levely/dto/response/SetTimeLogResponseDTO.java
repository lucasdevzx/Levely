package com.luken.levely.dto.response;

import java.util.UUID;

public record SetTimeLogResponseDTO(
        UUID id,
        Integer orderIndex,
        UUID dayTrainingWorkoutLogId,
        Integer durationSeconds
) {
}
