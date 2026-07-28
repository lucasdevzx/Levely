package com.luken.levely.dto.request;

import com.luken.levely.enums.GoalStatus;
import com.luken.levely.model.User;

import java.time.LocalDate;

public record GoalRequestDTO(
        Double startWeight,
        Double targetWeight,
        LocalDate deadline
) {
}
