package com.luken.levely.config;

import com.luken.levely.dto.request.*;
import com.luken.levely.enums.GoalType;
import com.luken.levely.model.*;
import com.luken.levely.repository.*;
import com.luken.levely.service.DayTrainingWorkoutLogService;
import com.luken.levely.service.DayTrainingWorkoutService;
import com.luken.levely.service.TrainingPlannerService;
import com.luken.levely.service.WorkoutService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class ConfigTest implements CommandLineRunner {

    private final TrainingPlannerService trainingPlannerService;
    private final TrainingPlannerRepository trainingPlannerRepository;

    private final DayTrainingRepository dayTrainingRepository;
    private final WorkoutRepository workoutRepository;
    private final DayTrainingWorkoutRepository dayTrainingWorkoutRepository;
    private final DayTrainingWorkoutLogRepository dayTrainingWorkoutLogRepository;

    private final WorkoutService workoutService;
    private final DayTrainingWorkoutService dayTrainingWorkoutService;
    private final DayTrainingWorkoutLogService dayTrainingWorkoutLogService;

    @Override
    public void run(String... args) throws Exception {

        TrainingPlannerRequestDTO body = new TrainingPlannerRequestDTO(
                "Verão",
                GoalType.HYPERTROPHY,
                LocalDate.of(2026, 7, 28),
                LocalDate.of(2026, 8, 4)
        );
        trainingPlannerService.createPlanner(body);

        Workout workout = new Workout("Tricpes", "ADS", 1);
        workoutRepository.save(workout);

        DayTrainingRequestDTO dayTrainingRequestDTO = new DayTrainingRequestDTO("Dia de Peito", "ads", DayOfWeek.FRIDAY);
        DayTraining dayTraining = DayTraining.create(dayTrainingRequestDTO);
        dayTrainingRepository.save(dayTraining);

        DayTrainingWorkoutRequestDTO dayTrainingWorkoutRequestDTO = new DayTrainingWorkoutRequestDTO(2);
        var dayTrainingWorkout =  dayTrainingWorkoutService.createDayTrainingWorkout(
                dayTraining.getId(),
                workout.getId(),
                dayTrainingWorkoutRequestDTO
        );

        var dayTrainingWorkoutLog = dayTrainingWorkoutLogService.createDayTrainingWorkoutLog(dayTrainingWorkout.getId());

        SetRepLogRequestDTO setRepLogRequestDTO = new SetRepLogRequestDTO(
                1,
                12,
                18.5
        ) ;

        var setRepLog = dayTrainingWorkoutLogService.addSetLog(
                dayTrainingWorkoutLog.getId(),
                setRepLogRequestDTO);



        WorkoutRequestDTO workoutRequestDTO = new WorkoutRequestDTO("asd", "asd", 1);

        Workout workout1 = workoutService.createWorkout(workoutRequestDTO);

    }
}