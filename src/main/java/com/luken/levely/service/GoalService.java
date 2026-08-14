package com.luken.levely.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.controller.exception.ApiError;
import com.luken.levely.dto.request.GoalRequestDTO;
import com.luken.levely.mapper.GoalMapper;
import com.luken.levely.model.Goal;
import com.luken.levely.repository.GoalRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    private final WorkoutService workoutService;
    private final AuthenticatedUser authenticatedUser;

    public Page<Goal> findAllMe(int page, int size) {
        var user = authenticatedUser.getAuthenticatedUser();
        return goalRepository.findAllByUserId(PageRequest.of(page, size), user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity goal not found by user id: " + user.getId()), ApiError.RESOURCE_NOT_FOUND));
    }

    public Page<Goal> findAllByWorkoutId(int page, int size, UUID workoutId) {
        var workout = workoutService.findById(workoutId);

        authenticatedUser.ownershipValidator(workout.getUser());
        return goalRepository.findAllByWorkoutId(PageRequest.of(page, size), workout.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity goal not found by workout id: " + workoutId), ApiError.RESOURCE_NOT_FOUND));
    }

    public Goal findById(UUID goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity goal not found by id: " + goalId), ApiError.RESOURCE_NOT_FOUND));
    }

    public Goal createGoal(GoalRequestDTO body, UUID workoutId) {
        var user = authenticatedUser.getAuthenticatedUser();
        var workout = workoutService.findById(workoutId);
        var goal = goalMapper.toEntity(body, user, workout);

        authenticatedUser.ownershipValidator(workout.getUser());
        return goalRepository.save(goal);
    }

    public Goal updateGoal(GoalRequestDTO body, UUID goalId) {
        var goal = findById(goalId);
        authenticatedUser.ownershipValidator(goal.getUser());

        goal.update(body);
        return goalRepository.save(goal);
    }

    public void deleteGoal(UUID goalId) {
        var goal = findById(goalId);
        authenticatedUser.ownershipValidator(goal.getUser());

        goalRepository.deleteById(goalId);
    }

}
