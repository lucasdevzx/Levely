package com.luken.levely.goal.dto;

import java.time.LocalDate;

public record GoalRequestDTO(
        Double startWeight,
        Double targetWeight,
        LocalDate deadline
) {
}
