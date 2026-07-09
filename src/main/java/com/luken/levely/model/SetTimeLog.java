package com.luken.levely.model;

import com.luken.levely.dto.request.SetTimeLogRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "set_times_logs")
@SuperBuilder
@NoArgsConstructor
@Getter
public class SetTimeLog extends SetLog {

    @NonNull
    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    public static SetTimeLog create(DayTrainingWorkoutLog dayTrainingWorkoutLog, SetTimeLogRequestDTO body) {
        return SetTimeLog
                .builder()
                .orderIndex(body.orderIndex())
                .dayTrainingWorkoutLog(dayTrainingWorkoutLog)
                .durationSeconds(body.durationSeconds())
                .build();
    }
}