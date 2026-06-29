package com.luken.levely.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "set_reps")
public class SetRep extends SetWorkout{

    @Column(name = "reps", nullable = false)
    private Integer reps;

    @Column(name = "weight", nullable = false)
    private Double weight;
}
