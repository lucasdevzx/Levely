package com.luken.levely.service;

import com.luken.levely.dto.request.DayTrainingRequestDTO;
import com.luken.levely.model.DayTraining;
import com.luken.levely.repository.DayTrainingRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayTrainingService {

    private final DayTrainingRepository dayTrainingRepository;
    private final AuthenticatedUser authenticatedUser;

    public Page<DayTraining> findAll(int page, int size) {
        return dayTrainingRepository.findAll(PageRequest.of(page, size));
    }

    public DayTraining findById(UUID dayTrainingId) {
          return dayTrainingRepository.findById(dayTrainingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity day training not found by id: " + dayTrainingId)));
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