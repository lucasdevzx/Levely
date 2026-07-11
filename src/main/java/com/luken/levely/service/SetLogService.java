package com.luken.levely.service;

import com.luken.levely.model.SetLog;
import com.luken.levely.repository.SetLogRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity setlog not found by id: " + setLogId)));
    }

    public void deleteSetLog(UUID setLogId) {
        var setLog = findById(setLogId);
        var user = setLog.getDayTrainingWorkoutLog().getWorkout().getUser();
        authenticatedUser.ownershipValidator(user);

        setLogRepository.deleteById(setLogId);
    }
}