package com.luken.levely.setlog.dto;

public record SetTimeLogRequestDTO(
        Integer orderIndex,
        Integer durationSeconds) implements SetLogRequestDTO {
}
