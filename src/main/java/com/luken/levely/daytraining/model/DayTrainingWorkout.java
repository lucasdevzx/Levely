    package com.luken.levely.daytraining.model;

    import com.luken.levely.daytraining.dto.DayTrainingWorkoutRequestDTO;
    import com.luken.levely.workout.model.Workout;
    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import java.time.LocalDateTime;
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

        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "day_training_id", nullable = false)
        @NonNull
        private DayTraining dayTraining;

        @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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

        public void importReset(DayTraining dayTraining, Workout workout) {
            id = null;
            this.dayTraining = dayTraining;
            this.workout = workout;
        }

        public void update(DayTrainingWorkoutRequestDTO body) {
            orderIndex = body.orderIndex();
        }


    }