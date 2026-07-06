package com.luken.levely.model;

import com.luken.levely.dto.request.DayTrainingRequestDTO;
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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DayTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NonNull
    @Column(name = "notes")
    private String notes;

    @NonNull
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "quantity_workout")
    private int quantityWorkout = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private TrainingPlanner trainingPlanner;

    @OneToMany(mappedBy = "dayTraining")
    private List<DayTrainingWorkout> dayTrainingWorkouts;

    public void associatePlanner(TrainingPlanner trainingPlanner) {
        this.trainingPlanner = trainingPlanner;
    }

    public static DayTraining create(DayTrainingRequestDTO body) {
        return new DayTraining(body.name(), body.notes(), body.dayOfWeek());
    }

    public void update(DayTrainingRequestDTO body) {
        name = body.name();
        notes = body.notes();
        dayOfWeek = body.dayOfWeek();
    }
}