package com.luken.levely.user.metrics.dto;

public record BodyStatsRequestDTO(
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
        Double rightThigh
) {
}
