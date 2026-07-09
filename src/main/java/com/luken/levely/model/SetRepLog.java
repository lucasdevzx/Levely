package com.luken.levely.model;

import com.luken.levely.dto.request.SetRepLogRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "set_reps_logs")
@SuperBuilder
@NoArgsConstructor
@Getter
public class SetRepLog extends SetLog {

    @Column(name = "reps", nullable = false)
    @NonNull
    private Integer reps;

    @NonNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    public static SetRepLog create(DayTrainingWorkoutLog dayTrainingWorkoutLog, SetRepLogRequestDTO body) {
        return SetRepLog
                .builder()
                .orderIndex(body.orderIndex())
                .dayTrainingWorkoutLog(dayTrainingWorkoutLog)
                .reps(body.reps())
                .weight(body.weight())
                .build();
    }
}