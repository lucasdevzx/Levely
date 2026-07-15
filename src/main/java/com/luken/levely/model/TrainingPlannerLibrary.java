package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "training_planner_library")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrainingPlannerLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @OneToOne
    private TrainingPlanner trainingPlanner;

    @NonNull
    @ManyToOne
    private Library library;

    // When create method for delete PlannerLibrary, add delete for Likes
    @OneToMany(mappedBy = "trainingPlannerLibrary")
    private List<LikeTrainingPlannerLibrary> likeTrainingPlannerLibraries;

    // When create method for delete PlannerLibrary, add delete for Saveds
    @OneToMany(mappedBy = "trainingPlannerLibrary")
    private List<SavedTrainingPlannerLibrary> savedTrainingPlannerLibraries;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static TrainingPlannerLibrary create(TrainingPlanner trainingPlanner, Library library) {
        return new TrainingPlannerLibrary(
                trainingPlanner,
                library
        );
    }

}
