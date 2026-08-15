package com.luken.levely.progress;

import com.luken.levely.daytraining.service.DayTrainingWorkoutLogService;
import com.luken.levely.progress.dto.ProgressTrainingRequestDTO;
import com.luken.levely.progress.dto.ProgressTrainingResponseDTO;
import com.luken.levely.setlog.model.SetRepLog;
import com.luken.levely.security.auth.AuthenticatedUser;
import com.luken.levely.setlog.service.SetRepLogService;
import com.luken.levely.progress.strategy.ProgressTrainingFactory;
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
