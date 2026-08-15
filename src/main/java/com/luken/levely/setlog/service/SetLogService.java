package com.luken.levely.setlog.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.setlog.model.SetLog;
import com.luken.levely.setlog.repository.SetLogRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetLogService {

    private final SetLogRepository setLogRepository;

    private final AuthenticatedUser authenticatedUser;

    public SetLog findById(UUID setLogId) {
        return setLogRepository.findById(setLogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity setlog not found by id: " + setLogId), ApiError.RESOURCE_NOT_FOUND));
    }

    public void deleteSetLog(UUID setLogId) {
        var setLog = findById(setLogId);
        var user = setLog.getDayTrainingWorkoutLog().getWorkout().getUser();
        authenticatedUser.ownershipValidator(user);

        setLogRepository.deleteById(setLogId);
    }
}