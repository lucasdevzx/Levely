package com.luken.levely.service;

import com.luken.levely.dto.request.ProgressTrainingRequestDTO;
import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.enums.ProgressTrainingType;
import com.luken.levely.model.SetRepLog;
import com.luken.levely.security.auth.AuthenticatedUser;
import com.luken.levely.strategy.ProgressTrainingFactory;
import com.luken.levely.strategy.ProgressTrainingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgressTrainingService {

    private final DayTrainingWorkoutLogService dayTrainingWorkoutLogService;
    private final ProgressTrainingFactory progressTrainingFactory;
    private final SetRepLogService setRepLogService;
    private final AuthenticatedUser authenticatedUser;

    public ProgressTrainingResponseDTO calculateProgressTraining(UUID dayTrainingWorkoutLogId, ProgressTrainingRequestDTO body) {
        var dayTrainingWorkoutLog = dayTrainingWorkoutLogService.findById(dayTrainingWorkoutLogId);
        var workout = dayTrainingWorkoutLog.getWorkout();
        List<SetRepLog> setRepLogs = setRepLogService.findAll(dayTrainingWorkoutLogId);

        var userOwnerDayTraining = dayTrainingWorkoutLog.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkoutLog.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerWorkout);
        authenticatedUser.ownershipValidator(userOwnerDayTraining);

        var instanceProgressTraining = progressTrainingFactory.getProgressType(body.progressTrainingType());
        return instanceProgressTraining.calculateProgression(workout, setRepLogs);
    }
}
