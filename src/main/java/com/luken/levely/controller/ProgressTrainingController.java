package com.luken.levely.controller;

import com.luken.levely.dto.request.ProgressTrainingRequestDTO;
import com.luken.levely.dto.response.ProgressTrainingResponseDTO;
import com.luken.levely.enums.ProgressTrainingType;
import com.luken.levely.service.ProgressTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/progresstraining")
@RequiredArgsConstructor
public class ProgressTrainingController {

    private final ProgressTrainingService progressTrainingService;

    @GetMapping(value = "/{dayTrainingWorkoutLogId}")
    public ResponseEntity<ProgressTrainingResponseDTO> calculateProgressTraining(@PathVariable UUID dayTrainingWorkoutLogId, @RequestBody ProgressTrainingRequestDTO body) {
        var progressTraining = progressTrainingService.calculateProgressTraining(dayTrainingWorkoutLogId, body);
        return ResponseEntity.ok().body(progressTraining);
    }

}
