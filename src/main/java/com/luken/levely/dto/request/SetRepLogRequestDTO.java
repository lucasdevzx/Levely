package com.luken.levely.dto.request;

public record SetRepLogRequestDTO(
        Integer orderIndex,
        Integer reps,
        Double weight) implements SetLogRequestDTO {}
