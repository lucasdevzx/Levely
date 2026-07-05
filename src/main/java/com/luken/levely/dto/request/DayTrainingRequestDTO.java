package com.luken.levely.dto.request;

import java.time.DayOfWeek;

public record DayTrainingRequestDTO(
        String name,
        String notes,
        DayOfWeek dayOfWeek
) {
}
