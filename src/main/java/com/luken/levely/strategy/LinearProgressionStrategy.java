package com.luken.levely.strategy;

import com.luken.levely.common.exception.SetRepInvalidException;
import com.luken.levely.common.exception.SetRepWeightInvalidException;
import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.Workout;

import java.util.List;

public class LinearProgressionStrategy implements ProgressTrainingStrategy{

    @Override
    public ProgressTrainingResponseDTO calculateProgression(Workout workout, List<SetRepLog> setRepLogs) {
        int quantitySets = setRepLogs.size();
        double maxWeightSet = setRepLogs.getLast().getWeight();
        int targetReps = workout.getTargetReps();
        validateSet(setRepLogs, maxWeightSet, targetReps);

        maxWeightSet += workout.getRecommendedWeightIncrement();
        return new ProgressTrainingResponseDTO(
                quantitySets,
                maxWeightSet,
                targetReps
        );
    }

    private void validateSet(List<SetRepLog> setRepLogs, double maxWeightSet, int targetReps) {

        for (SetRepLog setRepLog : setRepLogs) {

            if (setRepLog.getWeight() != maxWeightSet) {
                throw new SetRepWeightInvalidException("All your sets need to have the same weight");
            }

            if (!(setRepLog.getReps() >= targetReps)) {
                throw new SetRepInvalidException("Reps that do not meet the minimum requirement");
            }

        }
    }

}
