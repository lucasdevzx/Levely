package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "set_logs")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
public abstract class SetLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "order_index", nullable = false)
    Integer orderIndex;

    @ManyToOne
    DayTrainingWorkoutLog dayTrainingWorkoutLog;



}