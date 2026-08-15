package com.luken.levely.strategy;

import com.luken.levely.progress.strategy.DoubleProgressionStrategy;
import com.luken.levely.setlog.exception.SetRepInvalidException;
import com.luken.levely.setlog.exception.SetRepWeightInvalidException;
import com.luken.levely.setlog.dto.SetRepLogRequestDTO;
import com.luken.levely.daytraining.model.DayTrainingWorkoutLog;
import com.luken.levely.setlog.model.SetRepLog;
import com.luken.levely.workout.model.Workout;
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
class DoubleProgressionStrategyTest {

    @InjectMocks
    DoubleProgressionStrategy doubleProgressionStrategy;

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
        int quantitySets = 3;

        // ASSERT + ACT
        assertThrows(SetRepWeightInvalidException.class,
                () -> doubleProgressionStrategy.validateSetRepLog(setRepLogs, maxWeightSet, quantitySets));
    }

    @Test
    void shouldThrowExceptionIfQuantityRepsIsLessThanEight() {

        // ARRANGE
        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog();

        SetRepLogRequestDTO body = new SetRepLogRequestDTO(
                1,
                7,
                12.5
        );

        SetRepLog setRepLog = SetRepLog.create(dayTrainingWorkoutLog, body);
        List<SetRepLog> setRepLogs = Collections.nCopies(3, setRepLog);

        double maxWeightSet = 12.5;
        int quantitySets = 3;

        // ASSERT + ACT
        var exception = assertThrows(SetRepInvalidException.class,
                () -> doubleProgressionStrategy.validateSetRepLog(setRepLogs, maxWeightSet, quantitySets));
        assertEquals("Sets that do not meet the minimum requirement of eight", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfQuantityRepsIsLessThanTwelve() {

        // ARRANGE
        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog();

        SetRepLogRequestDTO body = new SetRepLogRequestDTO(
                1,
                8,
                12.5
        );

        SetRepLog setRepLog = SetRepLog.create(dayTrainingWorkoutLog, body);
        List<SetRepLog> setRepLogs = Collections.nCopies(3, setRepLog);

        double maxWeightSet = 12.5;
        int quantitySets = 3;

        // ASSERT + ACT
        var exception = assertThrows(SetRepInvalidException.class,
                () -> doubleProgressionStrategy.validateSetRepLog(setRepLogs, maxWeightSet, quantitySets));
        assertEquals("Sets that do not meet the minimum requirement of twelve", exception.getMessage());
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
        BDDMockito.when(workout.getRecommendedWeightIncrement()).thenReturn(2.5);

        // ACT
        var progressTraining = doubleProgressionStrategy.calculateProgression(workout, setRepLogs);

        // ASSERT
        assertNotNull(progressTraining);
        assertEquals(15, progressTraining.recommendedWeight());
    }



}