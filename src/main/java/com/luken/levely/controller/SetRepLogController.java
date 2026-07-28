package com.luken.levely.controller;

import com.luken.levely.dto.response.SetRepLogResponseDTO;
import com.luken.levely.mapper.SetRepLogMapper;
import com.luken.levely.service.SetRepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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