package com.luken.levely.config;

import com.luken.levely.dto.request.DayTrainingRequestDTO;
import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.enums.GoalType;
import com.luken.levely.model.*;
import com.luken.levely.repository.*;
import com.luken.levely.service.TrainingPlannerService;
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






        DayTrainingWorkoutLog dayTrainingWorkoutLog = new DayTrainingWorkoutLog(dayTraining, workout, 1);

        List<SetLog> setLogList = new ArrayList<>();

        SetLog setLog = SetRepLog
                .builder()
                .orderIndex(1)
                .dayTrainingWorkoutLog(dayTrainingWorkoutLog)
                .reps(12)
                .weight(12.5)
                .build();

        setLogList.add(setLog);

        dayTrainingWorkoutLog.setSetLogs(setLogList);
        dayTrainingWorkoutLogRepository.save(dayTrainingWorkoutLog);

    }
}