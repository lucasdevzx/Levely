package com.luken.levely.service;

import com.luken.levely.dto.request.DayTrainingRequestDTO;
import com.luken.levely.model.DayTraining;
import com.luken.levely.repository.DayTrainingRepository;
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

    public Page<DayTraining> findAll(int page, int size) {
        Page<DayTraining> dayTrainings = dayTrainingRepository.findAll(PageRequest.of(page, size));

        for (DayTraining dayTraining : dayTrainings) {
            dayTraining.calculateQuantityWorkout();
        }
        return dayTrainings;
    }

    public DayTraining findById(UUID dayTrainingId) {
        var dayTraining = dayTrainingRepository.findById(dayTrainingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity day training not found by id: " + dayTrainingId)));

        dayTraining.calculateQuantityWorkout();
        return dayTraining;
    }

    public DayTraining updateDayTraining(UUID dayTrainingId, DayTrainingRequestDTO body) {
        var dayTraining = findById(dayTrainingId);
        dayTraining.update(body);
        return dayTrainingRepository.save(dayTraining);
    }

    public void deleteDayTraining(UUID dayTrainingId) {
        dayTrainingRepository.deleteById(dayTrainingId);
    }
}