package com.luken.levely.dto.request;

public record WorkoutRequestDTO(
        String name,
        String description,
        Integer orderIndex
) {
}
