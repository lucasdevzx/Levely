package com.luken.levely.strategy;

import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.Workout;

import java.util.List;

public class UndulatingProgressionStrategy implements ProgressTrainingStrategy{

    @Override
    public ProgressTrainingResponseDTO calculateProgression(Workout workout, List<SetRepLog> setRepLogs) {
        return null;
    }
}
