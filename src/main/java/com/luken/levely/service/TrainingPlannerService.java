package com.luken.levely.service;

import com.luken.levely.dto.request.DayTrainingDefaultRequestDTO;
import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.dto.request.TrainingPlannerStatusRequestDTO;
import com.luken.levely.enums.GoalType;
import com.luken.levely.mapper.TrainingPlannerMapper;
import com.luken.levely.model.DayTraining;
import com.luken.levely.model.TrainingPlanner;
import com.luken.levely.repository.TrainingPlannerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<TrainingPlanner> findAll(int page, int size) {
        Page<TrainingPlanner> trainingPlanners = trainingPlannerRepository.findAll(PageRequest.of(page, size));

        for (TrainingPlanner trainingPlanner : trainingPlanners) {
            trainingPlanner.calculateCurrentWeek();
        }
        return trainingPlanners;
    }

    public TrainingPlanner findById(UUID trainingPlannerId) {
        TrainingPlanner trainingPlanner = trainingPlannerRepository.findById(trainingPlannerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity training planner not found by id: " + trainingPlannerId)));

        trainingPlanner.calculateCurrentWeek();
        return trainingPlanner;
    }

    public TrainingPlanner createPlanner(TrainingPlannerRequestDTO body) {
        TrainingPlanner trainingPlanner = trainingPlannerMapper.toEntity(body);
        trainingPlanner.calculateTotalWeeks();
        trainingPlanner.calculateCurrentWeek();
        return trainingPlannerRepository.save(trainingPlanner);
    }

    public DayTraining addDayTraining(UUID trainingPlannerId, DayTrainingDefaultRequestDTO body) {
        TrainingPlanner trainingPlanner = trainingPlannerRepository.findById(trainingPlannerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity training planner not found by id: " + trainingPlannerId)));

        trainingPlanner.addDayTraining(body.name(), body.dayOfWeek());
        trainingPlannerRepository.save(trainingPlanner);
        return trainingPlanner.getDayTrainings().getLast();
    }

    public TrainingPlanner updatePlanner(UUID trainingPlannerId, TrainingPlannerRequestDTO body) {
        TrainingPlanner trainingPlanner = trainingPlannerRepository.findById(trainingPlannerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity training planner not found by id: " + trainingPlannerId)));

        trainingPlanner.update(body);
        return trainingPlannerRepository.save(trainingPlanner);
    }

    public TrainingPlanner updatePlannerStatus(UUID trainingPlannerId, TrainingPlannerStatusRequestDTO body) {
        var trainingPlanner = trainingPlannerRepository.findById(trainingPlannerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity training planner not found by id: " + trainingPlannerId)));

        trainingPlanner.setPlannerStatus(body.plannerStatus());
        return trainingPlannerRepository.save(trainingPlanner);
    }

    public void deletePlanner(UUID trainingPlannerId) {
        trainingPlannerRepository.deleteById(trainingPlannerId);
    }
}