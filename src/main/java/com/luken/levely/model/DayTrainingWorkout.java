package com.luken.levely.model;

import com.luken.levely.dto.request.DayTrainingWorkoutRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.jdbc.Work;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "day_training_workouts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DayTrainingWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "day_training_id", nullable = false)
    @NonNull
    private DayTraining dayTraining;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @NonNull
    private Workout workout;

    @NonNull
    @JoinColumn(name = "order_index", nullable = false)
    private Integer orderIndex;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static DayTrainingWorkout create(DayTraining dayTraining, Workout workout, DayTrainingWorkoutRequestDTO body) {
        return new DayTrainingWorkout(
                dayTraining,
                workout,
                body.orderIndex()
        );
    }

    public void update(DayTrainingWorkoutRequestDTO body) {
        orderIndex = body.orderIndex();
    }


}