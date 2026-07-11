package com.luken.levely.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luken.levely.dto.request.WorkoutRequestDTO;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
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

    @NonNull
    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "workout", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DayTrainingWorkout> dayTrainingWorkouts;

    public static Workout create(WorkoutRequestDTO body) {
        return new Workout(
                body.name(),
                body.description(),
                body.orderIndex()
        );
    }

    public void update(WorkoutRequestDTO body) {
        name = body.name();
        description = body.description();
        orderIndex = body.orderIndex();
    }
}