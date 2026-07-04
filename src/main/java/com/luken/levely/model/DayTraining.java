package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "day_trainings")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@EqualsAndHashCode
public class DayTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "notes")
    private String notes;

    @NonNull
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    // TODO: Create method for calculate week number in training planner. Add nullable after create method for calculate
    @Column(name = "week_number")
    private Integer weekNumber;

    @Column(name = "quantity_workout")
    private Integer quantityWorkout;

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

    public void associatePlanner(TrainingPlanner trainingPlanner) {
        this.trainingPlanner = trainingPlanner;
    }
}