package com.luken.levely.progress.strategy;

import com.luken.levely.progress.dto.ProgressTrainingResponseDTO;
import com.luken.levely.progress.enums.ProgressTrainingType;
import com.luken.levely.setlog.model.SetRepLog;
import com.luken.levely.workout.model.Workout;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProgressTrainingStrategy {

    ProgressTrainingResponseDTO calculateProgression(Workout workout, List<SetRepLog> setRepLogs);

    ProgressTrainingType getProgressType();

}
