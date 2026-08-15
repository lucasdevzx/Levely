package com.luken.levely.setlog.controller;

import com.luken.levely.setlog.dto.SetTimeLogResponseDTO;
import com.luken.levely.setlog.mapper.SetTimeLogMapper;
import com.luken.levely.setlog.service.SetTimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/settimes")
@RequiredArgsConstructor
public class SetTimeLogController {

    private final SetTimeLogService setTimeLogService;
    private final SetTimeLogMapper setTimeLogMapper;

    @GetMapping(value = "/{dayTrainingWorkoutLogId}/all")
    public ResponseEntity<List<SetTimeLogResponseDTO>> findAll(@PathVariable UUID dayTrainingWorkoutLogId) {
        var setTimeLogs = setTimeLogService.findAll(dayTrainingWorkoutLogId);
        return ResponseEntity.ok().body(setTimeLogMapper.toDTOs(setTimeLogs));
    }

    @GetMapping(value = "/{setTimeLogId}")
    public ResponseEntity<SetTimeLogResponseDTO> findById(@PathVariable UUID setTimeLogId) {
        var setTimeLog = setTimeLogService.findById(setTimeLogId);
        return ResponseEntity.ok().body(setTimeLogMapper.toDTO(setTimeLog));
    }
}