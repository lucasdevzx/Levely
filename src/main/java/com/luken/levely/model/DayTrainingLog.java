package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "day_training_logs")
@RequiredArgsConstructor
@EqualsAndHashCode
public class DayTrainingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "notes")
    private String notes;

    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "week_number", nullable = false)
    private int weekNumber;

    @Column(name = "quantity_workout")
    private int quantityWorkout;

    @Column(name = "quantity_workout_completed")
    private int quantityWorkoutCompleted;

    @Column(name = "completed")
    private boolean completed;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private DayTraining dayTraining;

    @OneToMany(mappedBy = "dayTrainingLog")
    private List<WorkoutLog> workoutLogs;
}
