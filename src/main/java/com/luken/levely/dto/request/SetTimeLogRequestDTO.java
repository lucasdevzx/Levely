package com.luken.levely.dto.request;

public record SetTimeLogRequestDTO(
        Integer orderIndex,
        Integer durationSeconds) implements SetLogRequestDTO{
}
