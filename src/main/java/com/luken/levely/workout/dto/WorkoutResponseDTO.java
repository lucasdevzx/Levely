package com.luken.levely.workout.dto;

import com.luken.levely.workout.enums.WorkoutType;

import java.util.UUID;

public record  WorkoutResponseDTO(
        UUID id,
        String name,
        String description,
        WorkoutType workoutType,
        Double recommendedWeightIncrement,
        Integer orderIndex
) {
}