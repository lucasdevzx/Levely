package com.luken.levely.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "set_reps_logs")
@SuperBuilder
@NoArgsConstructor
public class SetRepLog extends SetLog {

    @Column(name = "reps", nullable = false)
    @NonNull
    private Integer reps;

    @NonNull
    @Column(name = "weight", nullable = false)
    private Double weight;

}