package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "day_training_workout_logs")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DayTrainingWorkoutLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "day_training_id")
    @NonNull
    private DayTraining dayTraining;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @NonNull
    private Workout workout;

    @NonNull
    private Integer orderIndex;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "dayTrainingWorkoutLog", cascade = CascadeType.ALL)
    @Setter
    private List<SetLog> setLogs;

    public void addSetLogs() {

    }

}
