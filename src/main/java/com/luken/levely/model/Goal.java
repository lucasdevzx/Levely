package com.luken.levely.model;

import com.luken.levely.dto.request.GoalRequestDTO;
import com.luken.levely.enums.GoalStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "goal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @Column(name = "start_weight")
    private Double startWeight;

    @NonNull
    @Column(name = "target_weight")
    private Double targetWeight;

    @NonNull
    @Column(name = "deadline")
    private LocalDate deadline;

    @NonNull
    @Column(name = "goal_status")
    @Enumerated(EnumType.STRING)
    private GoalStatus goalStatus = GoalStatus.PROGRESS;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @ManyToOne
    private Workout workout;

    public static Goal create(
            Double startWeight,
            Double targetWeight,
            LocalDate deadline,
            User user,
            Workout workout) {
        return new Goal(
                startWeight,
                targetWeight,
                deadline,
                user,
                workout
        );
    }

    public void update(GoalRequestDTO body) {
        startWeight = body.startWeight();
        targetWeight = body.targetWeight();
        deadline = body.deadline();
    }

    public void completeGoal() {
        goalStatus = GoalStatus.COMPLETED;
        completedAt = LocalDate.now();
    }

}
