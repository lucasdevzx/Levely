package com.luken.levely.dto.request;

import java.time.DayOfWeek;

public record DayTrainingDefaultRequestDTO(
        String name,
        DayOfWeek dayOfWeek
) {
}
