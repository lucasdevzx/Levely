package com.luken.levely.service;

import com.luken.levely.dto.request.DayTrainingWorkoutRequestDTO;
import com.luken.levely.mapper.DayTrainingWorkoutMapper;
import com.luken.levely.model.DayTrainingWorkout;
import com.luken.levely.model.User;
import com.luken.levely.repository.DayTrainingWorkoutRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayTrainingWorkoutService {

    private final DayTrainingWorkoutRepository dayTrainingWorkoutRepository;
    private final DayTrainingWorkoutMapper dayTrainingWorkoutMapper;

    private final DayTrainingService dayTrainingService;
    private final WorkoutService workoutService;
    private final AuthenticatedUser authenticatedUser;

    public Page<DayTrainingWorkout> findAll(int page, int size) {
        return dayTrainingWorkoutRepository.findAll(PageRequest.of(page, size));
    }

    public DayTrainingWorkout findById(UUID dayTrainingWorkoutId) {
        return dayTrainingWorkoutRepository.findById(dayTrainingWorkoutId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity day training workout not found by id: " + dayTrainingWorkoutId)));
    }

    public DayTrainingWorkout createDayTrainingWorkout(UUID dayTrainingId, UUID workoutId, DayTrainingWorkoutRequestDTO body) {
        var dayTraining = dayTrainingService.findById(dayTrainingId);
        var workout = workoutService.findById(workoutId);

        var userOwnerDayTraining = dayTraining.getTrainingPlanner().getUser();
        var userOwnerWorkout = workout.getUser();

        authenticatedUser.ownershipValidator(userOwnerDayTraining);
        authenticatedUser.ownershipValidator(userOwnerWorkout);

        var dayTrainingWorkout = dayTrainingWorkoutMapper.toEntity(dayTraining, workout, body);
        return dayTrainingWorkoutRepository.save(dayTrainingWorkout);
    }

    public DayTrainingWorkout updateDayTrainingWorkout(UUID dayTrainingWorkoutId, DayTrainingWorkoutRequestDTO body) {
        var dayTrainingWorkout = findById(dayTrainingWorkoutId);
        var userOwnerDayTraining = dayTrainingWorkout.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkout.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerDayTraining);
        authenticatedUser.ownershipValidator(userOwnerWorkout);

        dayTrainingWorkout.update(body);
        return dayTrainingWorkoutRepository.save(dayTrainingWorkout);
    }

    public void deleteDayTrainingWorkout(UUID dayTrainingWorkoutId) {
        var dayTrainingWorkout = findById(dayTrainingWorkoutId);
        var userOwnerDayTraining = dayTrainingWorkout.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkout.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerDayTraining);
        authenticatedUser.ownershipValidator(userOwnerWorkout);

        dayTrainingWorkoutRepository.deleteById(dayTrainingWorkoutId);
    }
}