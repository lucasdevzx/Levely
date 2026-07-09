package com.luken.levely.service;

import com.luken.levely.model.SetLog;
import com.luken.levely.repository.SetLogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetLogService {

    private final SetLogRepository setLogRepository;

    public void deleteSetLog(UUID setLogId) {
        setLogRepository.deleteById(setLogId);
    }
}