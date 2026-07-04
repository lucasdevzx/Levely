package com.luken.levely.service;

import com.luken.levely.dto.request.DayTrainingDefaultRequestDTO;
import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.enums.GoalType;
import com.luken.levely.mapper.TrainingPlannerMapper;
import com.luken.levely.model.DayTraining;
import com.luken.levely.model.TrainingPlanner;
import com.luken.levely.repository.TrainingPlannerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingPlannerService {

    private final TrainingPlannerRepository trainingPlannerRepository;
    private final TrainingPlannerMapper trainingPlannerMapper;

    public TrainingPlanner createPlanner(TrainingPlannerRequestDTO body) {
        TrainingPlanner trainingPlanner = trainingPlannerMapper.toEntity(body);
        return trainingPlannerRepository.save(trainingPlanner);
    }

    public DayTraining addDayTraining(UUID trainingPlannerId, DayTrainingDefaultRequestDTO body) {
        TrainingPlanner trainingPlanner = trainingPlannerRepository.findById(trainingPlannerId)
                .orElseThrow(() -> new EntityNotFoundException("Entity training planner not found by id"));

        trainingPlanner.addDayTraining(body.name(), body.dayOfWeek());
        trainingPlannerRepository.save(trainingPlanner);
        return trainingPlanner.getDayTrainings().getLast();
    }

}