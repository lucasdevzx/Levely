package com.luken.levely.strategy;

import com.luken.levely.common.exception.SetRepInvalidException;
import com.luken.levely.common.exception.SetRepWeightInvalidException;
import com.luken.levely.dto.request.SetRepLogRequestDTO;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.Workout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LinearProgressionStrategyTest {

    @InjectMocks
    LinearProgressionStrategy linearProgressionStrategy;

    @Mock
    Workout workout;

    @Test
    void shouldThrowExceptionIfWeightIsDifferent() {

        // ARRANGE
        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog();

        SetRepLogRequestDTO body = new SetRepLogRequestDTO(
                1,
                12,
                12.5
        );

        SetRepLog setRepLog = SetRepLog.create(dayTrainingWorkoutLog, body);
        List<SetRepLog> setRepLogs = Collections.nCopies(3, setRepLog);

        double maxWeightSet = 22.5;
        int targetReps = 12;

        // ASSERT + ACT
        assertThrows(SetRepWeightInvalidException.class,
                () -> linearProgressionStrategy.validateSetRepLog(setRepLogs, maxWeightSet, targetReps));
    }

    @Test
    void shouldThrowExceptionIfWeightIsLessThanOfTargetReps() {

        // ARRANGE
        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog();

        SetRepLogRequestDTO body = new SetRepLogRequestDTO(
                1,
                3,
                12.5
        );

        SetRepLog setRepLog = SetRepLog.create(dayTrainingWorkoutLog, body);
        List<SetRepLog> setRepLogs = Collections.nCopies(3, setRepLog);

        double maxWeightSet = 12.5;
        int targetReps = 5;

        // ASSERT + ACT
        assertThrows(SetRepInvalidException.class,
                () -> linearProgressionStrategy.validateSetRepLog(setRepLogs, maxWeightSet, targetReps));
    }

    @Test
    void shouldReturnNewWeightIncremented() {

        // ARRANGE
        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog();

        SetRepLogRequestDTO body = new SetRepLogRequestDTO(
                1,
                12,
                12.5
        );

        SetRepLog setRepLog = SetRepLog.create(dayTrainingWorkoutLog, body);
        List<SetRepLog> setRepLogs = Collections.nCopies(3, setRepLog);
        BDDMockito.when(workout.getTargetReps()).thenReturn(12);
        BDDMockito.when(workout.getRecommendedWeightIncrement()).thenReturn(2.5);

        // ACT
        var progressTraining = linearProgressionStrategy.calculateProgression(workout, setRepLogs);

        // ASSERT
        assertNotNull(progressTraining);
        assertEquals(15, progressTraining.recommendedWeight());
    }

    @Test
    void shouldReturnQuantityOfTargetReps() {

        // ARRANGE
        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog();

        SetRepLogRequestDTO body = new SetRepLogRequestDTO(
                1,
                12,
                12.5
        );

        SetRepLog setRepLog = SetRepLog.create(dayTrainingWorkoutLog, body);
        List<SetRepLog> setRepLogs = Collections.nCopies(3, setRepLog);
        BDDMockito.when(workout.getTargetReps()).thenReturn(12);
        BDDMockito.when(workout.getRecommendedWeightIncrement()).thenReturn(2.5);

        // ACT
        var progressTraining = linearProgressionStrategy.calculateProgression(workout, setRepLogs);

        // ASSERT
        assertNotNull(progressTraining);
        assertEquals(12, progressTraining.recommendedReps());
    }

}