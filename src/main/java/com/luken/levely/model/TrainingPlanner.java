package com.luken.levely.model;

import com.luken.levely.enums.GoalType;
import com.luken.levely.enums.PlannerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "training_planners")
@RequiredArgsConstructor
@EqualsAndHashCode
public class TrainingPlanner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "goal_type", nullable = false)
    private GoalType goalType;

    @Column(name = "planner_status", nullable = false)
    private PlannerStatus plannerStatus;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "trainingPlanner")
    private List<DayTraining> dayTrainingList;

}
