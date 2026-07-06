package com.luken.levely.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "set_times_logs")
@SuperBuilder
public class SetTimeLog extends SetLog {

    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;


}
