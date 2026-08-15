package com.luken.levely.setlog.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.setlog.model.SetRepLog;
import com.luken.levely.setlog.repository.SetRepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetRepLogService {

    private final SetRepLogRepository setRepLogRepository;

    public SetRepLog findById(UUID setRepLogId) {
        return setRepLogRepository.findById(setRepLogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity set rep log not found by id: " + setRepLogId), ApiError.RESOURCE_NOT_FOUND));
    }

    public List<SetRepLog> findAll(UUID dayTrainingWorkoutLogId) {
        return setRepLogRepository.findByDayTrainingWorkoutLogId(dayTrainingWorkoutLogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity set rep log not found by day training workout log id: " + dayTrainingWorkoutLogId), ApiError.RESOURCE_NOT_FOUND));
    }
}
