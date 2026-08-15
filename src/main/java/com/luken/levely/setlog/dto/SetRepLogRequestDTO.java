package com.luken.levely.setlog.dto;

public record SetRepLogRequestDTO(
        Integer orderIndex,
        Integer reps,
        Double weight) implements SetLogRequestDTO {}
