package com.luken.levely.progress;

import com.luken.levely.progress.dto.ProgressTrainingRequestDTO;
import com.luken.levely.progress.dto.ProgressTrainingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
