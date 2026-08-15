package com.luken.levely.user.metrics.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BodyStatsResponseDTO(
        UUID id,
        Double height,
        Double bodyFatPercentage,
        Double muscleMass,
        Double neck,
        Double chest,
        Double waist,
        Double hip,
        Double leftArm,
        Double rightArm,
        Double leftThigh,
        Double rightThigh,
        LocalDateTime createdAt,
        UUID userId
) {
}
