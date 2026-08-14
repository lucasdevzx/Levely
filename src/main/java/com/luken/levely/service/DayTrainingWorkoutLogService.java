package com.luken.levely.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.controller.exception.ApiError;
import com.luken.levely.dto.request.SetLogRequestDTO;
import com.luken.levely.dto.request.SetRepLogRequestDTO;
import com.luken.levely.dto.request.SetTimeLogRequestDTO;
import com.luken.levely.mapper.DayTrainingWorkoutLogMapper;
import com.luken.levely.model.*;
import com.luken.levely.repository.DayTrainingWorkoutLogRepository;
import com.luken.levely.repository.SetLogRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayTrainingWorkoutLogService {

    private final DayTrainingWorkoutLogRepository dayTrainingWorkoutLogRepository;
    private final DayTrainingWorkoutLogMapper dayTrainingWorkoutLogMapper;

    private final DayTrainingWorkoutService dayTrainingWorkoutService;
    private final SetLogRepository setLogRepository;
    private final AuthenticatedUser authenticatedUser;

    public Page<DayTrainingWorkoutLog> findAll(int page, int size) {
        return dayTrainingWorkoutLogRepository.findAll(PageRequest.of(page, size));
    }

    public List<DayTrainingWorkoutLog> findAllByCompletedTrue() {
        return dayTrainingWorkoutLogRepository.findAllByCompletedTrueOrderByCreatedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entity day training workout log not found", ApiError.RESOURCE_NOT_FOUND));
    }

    public DayTrainingWorkoutLog findById(UUID dayTrainingWorkoutLogId) {
        return dayTrainingWorkoutLogRepository.findById(dayTrainingWorkoutLogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity day training workout log not found by id: " + dayTrainingWorkoutLogId), ApiError.RESOURCE_NOT_FOUND));
    }

    public DayTrainingWorkoutLog createDayTrainingWorkoutLog(UUID dayTrainingWorkoutId) {
        var dayTrainingWorkout = dayTrainingWorkoutService.findById(dayTrainingWorkoutId);
        var userOwnerDayTraining = dayTrainingWorkout.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkout.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerWorkout);
        authenticatedUser.ownershipValidator(userOwnerDayTraining);

        var dayTrainingWorkoutLog = DayTrainingWorkoutLog.create(dayTrainingWorkout);
        return dayTrainingWorkoutLogRepository.save(dayTrainingWorkoutLog);
    }

    @Transactional
    public SetLog addSetLog(UUID dayTrainingWorkoutLogId, SetLogRequestDTO body) {
        var dayTrainingWorkoutLog = findById(dayTrainingWorkoutLogId);
        var userOwnerDayTraining = dayTrainingWorkoutLog.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkoutLog.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerWorkout);
        authenticatedUser.ownershipValidator(userOwnerDayTraining);

        var setLog = setLogFactory(dayTrainingWorkoutLog, body);
        setLog.associateDayTrainingWorkoutLog(dayTrainingWorkoutLog);

        setLogRepository.save(setLog);
        dayTrainingWorkoutLog.addSetLogs(setLog);
        return setLog;
    }

    public SetLog setLogFactory(DayTrainingWorkoutLog dayTrainingWorkoutLog, SetLogRequestDTO body) {

        if (body instanceof SetRepLogRequestDTO setLogRequestDTO) {
            return SetRepLog.create(dayTrainingWorkoutLog, setLogRequestDTO);
        }

        if (body instanceof SetTimeLogRequestDTO setTimeLogRequestDTO) {
            return SetTimeLog.create(dayTrainingWorkoutLog, setTimeLogRequestDTO);
        }

        return null;
    }

    public DayTrainingWorkoutLog updateCompleteDayTrainingWorkoutLog(UUID dayTrainingWorkoutLogId) {
        var dayTrainingWorkoutLog = findById(dayTrainingWorkoutLogId);
        var userOwnerDayTraining = dayTrainingWorkoutLog.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkoutLog.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerWorkout);
        authenticatedUser.ownershipValidator(userOwnerDayTraining);

        dayTrainingWorkoutLog.setCompleted(true);
        return dayTrainingWorkoutLogRepository.save(dayTrainingWorkoutLog);
    }

    public void deleteDayTrainingWorkoutLog(UUID dayTrainingWorkoutLogId) {
        var dayTrainingWorkoutLog = findById(dayTrainingWorkoutLogId);
        var userOwnerDayTraining = dayTrainingWorkoutLog.getDayTraining().getTrainingPlanner().getUser();
        var userOwnerWorkout = dayTrainingWorkoutLog.getWorkout().getUser();

        authenticatedUser.ownershipValidator(userOwnerWorkout);
        authenticatedUser.ownershipValidator(userOwnerDayTraining);

        dayTrainingWorkoutLogRepository.deleteById(dayTrainingWorkoutLogId);
    }
}