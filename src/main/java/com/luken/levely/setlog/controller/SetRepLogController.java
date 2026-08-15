package com.luken.levely.setlog.controller;

import com.luken.levely.setlog.dto.SetRepLogResponseDTO;
import com.luken.levely.setlog.mapper.SetRepLogMapper;
import com.luken.levely.setlog.service.SetRepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/setreps")
@RequiredArgsConstructor
public class SetRepLogController {

    private final SetRepLogService setRepLogService;
    private final SetRepLogMapper setRepLogMapper;

    @GetMapping(value = "/{dayTrainingWorkoutLogId}/all")
    public ResponseEntity<List<SetRepLogResponseDTO>> findAll(@PathVariable UUID dayTrainingWorkoutLogId) {
        var setRepLogs = setRepLogService.findAll(dayTrainingWorkoutLogId);
        return ResponseEntity.ok().body(setRepLogMapper.toDTOs(setRepLogs));
    }

    @GetMapping(value = "/{setRepLogId}")
    public ResponseEntity<SetRepLogResponseDTO> findById(@PathVariable UUID setRepLogId) {
        var setRepLog = setRepLogService.findById(setRepLogId);
        return ResponseEntity.ok().body(setRepLogMapper.toDTO(setRepLog));

    }
}