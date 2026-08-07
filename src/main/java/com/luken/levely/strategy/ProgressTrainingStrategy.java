package com.luken.levely.strategy;

import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.enums.ProgressTrainingType;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.Workout;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProgressTrainingStrategy {

    ProgressTrainingResponseDTO calculateProgression(Workout workout, List<SetRepLog> setRepLogs);

    ProgressTrainingType getProgressType();

}
