package com.luken.levely.strategy;

import com.luken.levely.common.exception.SetRepInvalidException;
import com.luken.levely.common.exception.SetRepWeightInvalidException;
import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.model.SetLog;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.Workout;

import java.util.List;

public class DoubleProgressionStrategy implements ProgressTrainingStrategy{

    @Override
    public ProgressTrainingResponseDTO calculateProgression(Workout workout, List<SetRepLog> setRepLogs) {
        int quantitySets = setRepLogs.size();
        double maxWeightSet = setRepLogs.getLast().getWeight();
        validateSetRepLog(setRepLogs, maxWeightSet, quantitySets);

        maxWeightSet += workout.getRecommendedWeightIncrement();
        return new ProgressTrainingResponseDTO(
                quantitySets,
                maxWeightSet,
                8
        );
    }

    void validateSetRepLog(List<SetRepLog> setRepLogs, double maxWeightSet, int quantitySets) {

        for (SetRepLog setRepLog : setRepLogs) {

            if (setRepLog.getWeight() != maxWeightSet) {
                throw new SetRepWeightInvalidException("All your sets need to have the same weight");
            }

            if (setRepLog.getReps() < 8) {
                throw new SetRepInvalidException("Sets that do not meet the minimum requirement of eight");
            }

            if (setRepLog.getReps() < 12) {
                throw new SetRepInvalidException("Sets that do not meet the minimum requirement of twelve");
            }

        }
    }

}
