package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "day_trainings")
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class DayTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "notes")
    private String notes;

    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "week_number", nullable = false)
    private int weekNumber;

    @Column(name = "quantity_workout")
    private int quantityWorkout;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private TrainingPlanner trainingPlanner;

    @OneToMany(mappedBy = "dayTraining")
    private List<DayTrainingLog> dayTrainingLogs;

    @OneToMany(mappedBy = "dayTraining")
    private List<Workout> workouts;
}