package com.luken.levely.workout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luken.levely.daytraining.model.DayTrainingWorkout;
import com.luken.levely.workout.dto.WorkoutRequestDTO;
import com.luken.levely.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workouts")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NonNull
    @Column(name = "description")
    private String description;

    @Column(name = "recomended_weight_increment")
    private Double recommendedWeightIncrement = 2.5;

    @Column(name = "target_reps")
    private Integer targetReps = 5;

    @NonNull
    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NonNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "workout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DayTrainingWorkout> dayTrainingWorkouts;

    public static Workout create(WorkoutRequestDTO body, User user) {
        return new Workout(
                body.name(),
                body.description(),
                body.orderIndex(),
                user
        );
    }

    public void importReset(User user) {
        id = null;
        dayTrainingWorkouts = null;
        this.user = user;
    }

    public void update(WorkoutRequestDTO body) {
        name = body.name();
        description = body.description();
        orderIndex = body.orderIndex();
    }
}