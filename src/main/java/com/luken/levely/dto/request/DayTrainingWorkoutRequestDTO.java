package com.luken.levely.dto.request;

import com.luken.levely.model.DayTraining;
import com.luken.levely.model.Workout;

import java.util.UUID;

public record DayTrainingWorkoutRequestDTO(
        Integer orderIndex
) {
}
