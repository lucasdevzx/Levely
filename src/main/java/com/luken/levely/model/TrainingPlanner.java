package com.luken.levely.model;

import com.luken.levely.common.exception.InvalidActionException;
import com.luken.levely.dto.request.DayTrainingRequestDTO;
import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.dto.request.TrainingPlannerStatusRequestDTO;
import com.luken.levely.enums.GoalType;
import com.luken.levely.enums.PlannerStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "training_planners")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainingPlanner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NonNull
    @Column(name = "goal_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @Column(name = "planner_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private PlannerStatus plannerStatus = PlannerStatus.ACTIVE;

    @NonNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NonNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "total_weeks")
    private int totalWeeks;

    @Transient
    private int currentWeek;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "trainingPlanner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DayTraining> dayTrainings;

    public static TrainingPlanner create(TrainingPlannerRequestDTO body) {
        validateDate(body.startDate(), body.endDate());
        return new TrainingPlanner(
                body.name(),
                body.goalType(),
                body.startDate(),
                body.endDate());
    }

    public void addDayTraining(DayTrainingRequestDTO body) {
        if (plannerStatus == PlannerStatus.COMPLETED || plannerStatus == PlannerStatus.PAUSED) {
            throw new IllegalArgumentException("The planner status does not allow changes");
        }

        if (dayTrainings.size() == 7) {
            throw new IllegalArgumentException("The planner reached the maximum number of training days");
        }

        DayTraining dayTraining = DayTraining.create(body);
        dayTraining.associatePlanner(this);
        this.dayTrainings.add(dayTraining);
    }

    public void update(TrainingPlannerRequestDTO body) {
        validateDate(body.startDate(), body.endDate());

        name = body.name();
        goalType = body.goalType();
        startDate = body.startDate();
        endDate = body.endDate();

        calculateTotalWeeks();
        calculateCurrentWeek();
    }

    public static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("The end date cannot be before the start date.");
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The start date cannot be before the date now");
        }
    }

    public void calculateTotalWeeks() {
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        totalWeeks = (int) Math.ceil((double) totalDays / 7);
    }

    public void calculateCurrentWeek() {
        long totalDays = ChronoUnit.DAYS.between(startDate, LocalDate.now());
        currentWeek = (int) Math.ceil((double) (totalDays + 1) / 7);

        if (currentWeek <= 0) {
            currentWeek = 1;
        }
    }
}