package com.luken.levely.strategy;

import com.luken.levely.common.exception.SetRepInvalidException;
import com.luken.levely.common.exception.SetRepWeightInvalidException;
import com.luken.levely.controller.exception.ApiError;
import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.enums.ProgressTrainingType;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.model.SetLog;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.Workout;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    @Override
    public ProgressTrainingType getProgressType() {
        return ProgressTrainingType.DOUBLE;
    }

    void validateSetRepLog(List<SetRepLog> setRepLogs, double maxWeightSet, int quantitySets) {

        for (SetRepLog setRepLog : setRepLogs) {

            if (setRepLog.getWeight() != maxWeightSet) {
                throw new SetRepWeightInvalidException(
                        "All your sets need to have the same weight",
                        ApiError.SET_INVALID,
                        setRepLog.getWeight(),
                        maxWeightSet);
            }

            if (setRepLog.getReps() < 8) {
                throw new SetRepInvalidException(
                        "Sets that do not meet the minimum requirement of eight",
                        ApiError.SET_INVALID,
                        setRepLog.getReps(),
                        8);
            }

            if (setRepLog.getReps() < 12) {
                throw new SetRepInvalidException(
                        "Sets that do not meet the minimum requirement of twelve",
                        ApiError.SET_INVALID,
                        setRepLog.getReps(),
                        12);
            }
        }
    }

}
