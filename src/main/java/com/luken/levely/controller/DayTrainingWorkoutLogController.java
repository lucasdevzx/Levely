package com.luken.levely.controller;

import com.luken.levely.dto.response.DayTrainingWorkoutLogResponseDTO;
import com.luken.levely.dto.response.DayTrainingWorkoutResponseDTO;
import com.luken.levely.mapper.DayTrainingWorkoutLogMapper;
import com.luken.levely.model.DayTrainingWorkoutLog;
import com.luken.levely.service.DayTrainingWorkoutLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/daytrainingworkoutlogs")
@RequiredArgsConstructor
public class DayTrainingWorkoutLogController {

    private final DayTrainingWorkoutLogService dayTrainingWorkoutLogService;
    private final DayTrainingWorkoutLogMapper dayTrainingWorkoutLogMapper;

    @GetMapping
    public ResponseEntity<Page<DayTrainingWorkoutLogResponseDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<DayTrainingWorkoutLog> dayTrainingWorkoutLogs = dayTrainingWorkoutLogService.findAll(page, size);
        return ResponseEntity.ok().body(dayTrainingWorkoutLogs.map(dayTrainingWorkoutLogMapper::toDTO));
    }

    @GetMapping(value = "/{dayTrainingWorkoutId}")
    public ResponseEntity<DayTrainingWorkoutLogResponseDTO> findById(@PathVariable UUID dayTrainingWorkoutId) {
        var dayTrainingWorkoutLog = dayTrainingWorkoutLogService.findById(dayTrainingWorkoutId);
        return ResponseEntity.ok().body(dayTrainingWorkoutLogMapper.toDTO(dayTrainingWorkoutLog));
    }

    @PostMapping(value = "/{dayTrainingWorkoutId}")
    public ResponseEntity<DayTrainingWorkoutLogResponseDTO> createDayTrainingWorkoutLog(@PathVariable UUID dayTrainingWorkoutId) {
        var dayTrainingWorkoutLog = dayTrainingWorkoutLogService.createDayTrainingWorkoutLog(dayTrainingWorkoutId);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{dayTrainingWorkoutLogId}")
                .buildAndExpand(dayTrainingWorkoutLog.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dayTrainingWorkoutLogMapper.toDTO(dayTrainingWorkoutLog));
    }

    @DeleteMapping(value = "/{dayTrainingWorkoutLogId}")
    public ResponseEntity<Void> deleteDayTrainingWorkoutLog(@PathVariable UUID dayTrainingWorkoutLogId) {
        dayTrainingWorkoutLogService.deleteDayTrainingWorkoutLog(dayTrainingWorkoutLogId);
        return ResponseEntity.noContent().build();
    }
}
