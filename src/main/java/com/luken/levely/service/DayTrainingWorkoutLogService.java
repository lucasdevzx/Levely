package com.luken.levely.service;

import com.luken.levely.dto.request.DayTrainingWorkoutLogRequestDTO;
import com.luken.levely.dto.request.SetLogRequestDTO;
import com.luken.levely.dto.request.SetRepLogRequestDTO;
import com.luken.levely.dto.request.SetTimeLogRequestDTO;
import com.luken.levely.mapper.DayTrainingWorkoutLogMapper;
import com.luken.levely.model.*;
import com.luken.levely.repository.DayTrainingWorkoutLogRepository;
import com.luken.levely.repository.SetLogRepository;
import com.luken.levely.repository.SetRepLogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DayTrainingWorkoutLogService {

    private final DayTrainingWorkoutLogRepository dayTrainingWorkoutLogRepository;
    private final DayTrainingWorkoutLogMapper dayTrainingWorkoutLogMapper;

    private final DayTrainingWorkoutService dayTrainingWorkoutService;
    private final SetLogRepository setLogRepository;

    public Page<DayTrainingWorkoutLog> findAll(int page, int size) {
        return dayTrainingWorkoutLogRepository.findAll(PageRequest.of(page, size));
    }

    public DayTrainingWorkoutLog findById(UUID dayTrainingWorkoutLogId) {
        return dayTrainingWorkoutLogRepository.findById(dayTrainingWorkoutLogId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity day training workout log not found by id: " + dayTrainingWorkoutLogId)));
    }

    public DayTrainingWorkoutLog createDayTrainingWorkoutLog(UUID dayTrainingWorkoutId) {
        var dayTrainingWorkout = dayTrainingWorkoutService.findById(dayTrainingWorkoutId);
        var dayTrainingWorkoutLog = DayTrainingWorkoutLog.create(dayTrainingWorkout);
        return dayTrainingWorkoutLogRepository.save(dayTrainingWorkoutLog);
    }

    @Transactional
    public SetLog addSetLog(UUID dayTrainingWorkoutLogId, SetLogRequestDTO body) {
        var dayTrainingWorkoutLog = findById(dayTrainingWorkoutLogId);
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

    public void deleteDayTrainingWorkoutLog(UUID dayTrainingWorkoutLogId) {
        dayTrainingWorkoutLogRepository.deleteById(dayTrainingWorkoutLogId);
    }

}
