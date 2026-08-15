package com.luken.levely.setlog.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.setlog.model.SetTimeLog;
import com.luken.levely.setlog.repository.SetTimeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetTimeLogService {

    private final SetTimeLogRepository setTimeLogRepository;

    public SetTimeLog findById(UUID setTimeLogId) {
        return setTimeLogRepository.findById(setTimeLogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity set time log not found by id: " + setTimeLogId), ApiError.RESOURCE_NOT_FOUND));
    }

    public List<SetTimeLog> findAll(UUID dayTrainingWorkoutLogId) {
        return setTimeLogRepository.findByDayTrainingWorkoutLogId(dayTrainingWorkoutLogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity set time log not found by day training workout log id: " + dayTrainingWorkoutLogId), ApiError.RESOURCE_NOT_FOUND));
    }
}
