package com.luken.levely.daytraining.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.daytraining.dto.DayTrainingRequestDTO;
import com.luken.levely.daytraining.model.DayTraining;
import com.luken.levely.daytraining.repository.DayTrainingRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayTrainingService {

    private final DayTrainingRepository dayTrainingRepository;
    private final AuthenticatedUser authenticatedUser;

    public Page<DayTraining> findAll(int page, int size) {
        return dayTrainingRepository.findAll(PageRequest.of(page, size));
    }

    public List<DayTraining> findAllByTrainingPlannerId(UUID trainingPlannerId) {
        return dayTrainingRepository.findAllByTrainingPlannerId(trainingPlannerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity day training not found by training planner id: " + trainingPlannerId), ApiError.RESOURCE_NOT_FOUND));
    }

    public DayTraining findById(UUID dayTrainingId) {
          return dayTrainingRepository.findById(dayTrainingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity day training not found by id: " + dayTrainingId), ApiError.RESOURCE_NOT_FOUND));
    }

    public DayTraining updateDayTraining(UUID dayTrainingId, DayTrainingRequestDTO body) {
        var dayTraining = findById(dayTrainingId);
        var userOwner = dayTraining.getTrainingPlanner().getUser();
        authenticatedUser.ownershipValidator(userOwner);

        dayTraining.update(body);
        return dayTrainingRepository.save(dayTraining);
    }

    public void deleteDayTraining(UUID dayTrainingId) {
        var dayTraining = findById(dayTrainingId);
        var userOwner = dayTraining.getTrainingPlanner().getUser();
        authenticatedUser.ownershipValidator(userOwner);

        dayTrainingRepository.deleteById(dayTrainingId);
    }
}