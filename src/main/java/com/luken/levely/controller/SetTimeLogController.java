package com.luken.levely.controller;

import com.luken.levely.dto.response.SetRepLogResponseDTO;
import com.luken.levely.dto.response.SetTimeLogResponseDTO;
import com.luken.levely.mapper.SetTimeLogMapper;
import com.luken.levely.repository.SetTimeLogRepository;
import com.luken.levely.service.SetTimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/settimes")
@RequiredArgsConstructor
public class SetTimeLogController {

    private final SetTimeLogService setTimeLogService;
    private final SetTimeLogMapper setTimeLogMapper;

    @GetMapping(value = "/{dayTrainingWorkoutLogId}/all")
    public ResponseEntity<Stream<SetTimeLogResponseDTO>> findAll(@PathVariable UUID dayTrainingWorkoutLogId) {
        var setTimeLogs = setTimeLogService.findAll(dayTrainingWorkoutLogId);
        return ResponseEntity.ok().body(setTimeLogs.stream().map(setTimeLogMapper::toDTO));
    }

    @GetMapping(value = "/{setTimeLogId}")
    public ResponseEntity<SetTimeLogResponseDTO> findById(@PathVariable UUID setTimeLogId) {
        var setTimeLog = setTimeLogService.findById(setTimeLogId);
        return ResponseEntity.ok().body(setTimeLogMapper.toDTO(setTimeLog));
    }
}