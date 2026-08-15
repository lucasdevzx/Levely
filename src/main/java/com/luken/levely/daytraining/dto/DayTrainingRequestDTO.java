package com.luken.levely.daytraining.dto;

import java.time.DayOfWeek;

public record DayTrainingRequestDTO(
        String name,
        String notes,
        DayOfWeek dayOfWeek
) {
}
