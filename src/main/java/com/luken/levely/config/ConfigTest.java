package com.luken.levely.config;

import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.enums.GoalType;
import com.luken.levely.model.TrainingPlanner;
import com.luken.levely.repository.TrainingPlannerRepository;
import com.luken.levely.service.TrainingPlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class ConfigTest implements CommandLineRunner {

    private final TrainingPlannerService trainingPlannerService;
    private final TrainingPlannerRepository trainingPlannerRepository;

    @Override
    public void run(String... args) throws Exception {

        TrainingPlannerRequestDTO body = new TrainingPlannerRequestDTO(
                "Verão",
                GoalType.HYPERTROPHY,
                LocalDate.of(2026, 7, 28),
                LocalDate.of(2026, 8, 4)
        );
        trainingPlannerService.createPlanner(body);
    }
}