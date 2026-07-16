package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "saved_training_planner_library")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SavedTrainingPlannerLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Saved saved;

    @NonNull
    @ManyToOne
    private TrainingPlannerLibrary trainingPlannerLibrary;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static SavedTrainingPlannerLibrary create(Saved saved, TrainingPlannerLibrary trainingPlannerLibrary) {
        return new SavedTrainingPlannerLibrary(saved, trainingPlannerLibrary);
    }

}