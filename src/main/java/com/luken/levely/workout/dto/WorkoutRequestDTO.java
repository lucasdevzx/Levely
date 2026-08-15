package com.luken.levely.workout.dto;

public record WorkoutRequestDTO(
        String name,
        String description,
        Integer orderIndex
) {
}
