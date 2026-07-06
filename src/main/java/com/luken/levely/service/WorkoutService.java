package com.luken.levely.service;

import com.luken.levely.dto.request.WorkoutRequestDTO;
import com.luken.levely.mapper.WorkoutMapper;
import com.luken.levely.model.Workout;
import com.luken.levely.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public Page<Workout> findAll(int page, int size) {
        return workoutRepository
                .findAll(PageRequest.of(page, size));
    }

    public Workout findById(UUID workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity workout not found by id: " + workoutId)));
    }

    public Workout createWorkout(WorkoutRequestDTO body) {
        Workout workout = workoutMapper.toEntity(body);
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(UUID workoutId, WorkoutRequestDTO body) {
        var workout = findById(workoutId);
        workout.update(body);
        return workoutRepository.save(workout);
    }

    public void deleteWorkout(UUID workoutId) {
        workoutRepository.deleteById(workoutId);
    }
}
