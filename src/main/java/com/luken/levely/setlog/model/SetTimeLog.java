package com.luken.levely.setlog.model;

import com.luken.levely.daytraining.model.DayTrainingWorkoutLog;
import com.luken.levely.setlog.dto.SetTimeLogRequestDTO;
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
        if (dayTrainingWorkoutLog.getSetLogs().size() > 12) {
            throw new IllegalArgumentException("The maximum number of set has been reached.");
        }

        return SetTimeLog
                .builder()
                .orderIndex(body.orderIndex())
                .dayTrainingWorkoutLog(dayTrainingWorkoutLog)
                .durationSeconds(body.durationSeconds())
                .build();
    }
}