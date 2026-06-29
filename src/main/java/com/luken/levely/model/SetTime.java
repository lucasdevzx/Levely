package com.luken.levely.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "set_times")
public class SetTime extends SetWorkout{

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;
}
