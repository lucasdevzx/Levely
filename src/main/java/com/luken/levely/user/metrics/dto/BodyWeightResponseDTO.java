package com.luken.levely.user.metrics.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BodyWeightResponseDTO(
        UUID id,
        Double weight,
        LocalDateTime createdAt,
        UUID userId
) {
}
