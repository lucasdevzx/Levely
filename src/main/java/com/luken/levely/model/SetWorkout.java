package com.luken.levely.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "set_workout")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SetWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "order_index", nullable = false)
    Integer order;

    @ManyToOne
    WorkoutLog workoutLog;
}