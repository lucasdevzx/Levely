package com.luken.levely.workout.dto;

import com.luken.levely.workout.enums.WorkoutType;

public record WorkoutRequestDTO(
        String name,
        String description,
        WorkoutType workoutType,
        Integer orderIndex
) {
}
