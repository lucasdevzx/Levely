package com.luken.levely.model;

import com.luken.levely.dto.request.DayTrainingRequestDTO;
import com.luken.levely.enums.PlannerStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TrainingPlannerTest {

    @Nested
    class AddDayTrainig {

        private TrainingPlanner trainingPlanner;

        @BeforeEach
        void setUp() {
            trainingPlanner = new TrainingPlanner();
        }

        @Test
        void shouldThrowExceptionIfPlannerStatusIsCompleted() {

            // ARRANGE

            DayTrainingRequestDTO dto = new DayTrainingRequestDTO(
                    "Strength",
                    "Training of Strength",
                    DayOfWeek.FRIDAY
            );

            // ACT
            trainingPlanner.changePlannerStatus(PlannerStatus.COMPLETED);

            // ASSERT + ACT
            assertThrows(IllegalArgumentException.class, () -> trainingPlanner.addDayTraining(dto));
        }

        @Test
        void shouldThrowExceptionIfPlannerStatusIsPaused() {

            // ARRANGE

            DayTrainingRequestDTO dto = new DayTrainingRequestDTO(
                    "Strength",
                    "Training of Strength",
                    DayOfWeek.FRIDAY
            );

            // ACT
            trainingPlanner.changePlannerStatus(PlannerStatus.PAUSED);

            // ASSERT + ACT
            assertThrows(IllegalArgumentException.class, () -> trainingPlanner.addDayTraining(dto));
        }

        @Test
        void shouldThrowExceptionIfDayTrainingQuantityIsBiggerSeven() {

            // ARRANGE

            DayTrainingRequestDTO dto = new DayTrainingRequestDTO(
                    "Strength",
                    "Training of Strength",
                    DayOfWeek.FRIDAY
            );

            for (int i   = 0; i < 8; i++ ) {
                trainingPlanner.addDayTraining(dto);
            }

            // ASSERT + ACT
            assertThrows(IllegalArgumentException.class, () -> trainingPlanner.addDayTraining(dto));
        }

        @Test
        void shouldContainAssociatedPlannerInDayTraining() {

            // ARRANGE

            DayTrainingRequestDTO dto = new DayTrainingRequestDTO(
                    "Strength",
                    "Training of Strength",
                    DayOfWeek.FRIDAY
            );

            // ACT
            trainingPlanner.addDayTraining(dto);

            // ASSERT
            assertEquals(trainingPlanner, trainingPlanner.getDayTrainings().getLast().getTrainingPlanner());

        }

        @Test
        void shouldReturnDayTrainingQuantityDifferentOfZero() {

            // ARRANGE

            DayTrainingRequestDTO dto = new DayTrainingRequestDTO(
                    "Strength",
                    "Training of Strength",
                    DayOfWeek.FRIDAY
            );

            // ACT
            trainingPlanner.addDayTraining(dto);
            int actualQuantity = trainingPlanner.getDayTrainings().size();

            // ASSERT
            assertEquals(1, actualQuantity);

        }
    }




}