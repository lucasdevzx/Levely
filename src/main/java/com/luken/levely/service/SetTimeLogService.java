package com.luken.levely.service;

import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.SetTimeLog;
import com.luken.levely.repository.SetTimeLogRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity set time log not found by id: " + setTimeLogId)));
    }

    public List<SetTimeLog> findAll(UUID dayTrainingWorkoutLogId) {
        return setTimeLogRepository.findByDayTrainingWorkoutLogId(dayTrainingWorkoutLogId);
    }

}
