package com.luken.levely.trainingplanner.controller;

import com.luken.levely.daytraining.dto.DayTrainingRequestDTO;
import com.luken.levely.trainingplanner.dto.TrainingPlannerStatusRequestDTO;
import com.luken.levely.daytraining.dto.DayTrainingResponseDTO;
import com.luken.levely.trainingplanner.dto.TrainingPlannerRequestDTO;
import com.luken.levely.trainingplanner.dto.TrainingPlannerResponseDTO;
import com.luken.levely.daytraining.mapper.DayTrainingMapper;
import com.luken.levely.trainingplanner.mapper.TrainingPlannerMapper;
import com.luken.levely.daytraining.model.DayTraining;
import com.luken.levely.trainingplanner.model.TrainingPlanner;
import com.luken.levely.trainingplanner.service.TrainingPlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/trainingplanners")
@RequiredArgsConstructor
public class TrainingPlannerController {

    private final TrainingPlannerService trainingPlannerService;
    private final TrainingPlannerMapper trainingPlannerMapper;
    private final DayTrainingMapper dayTrainingMapper;

    @GetMapping
    public ResponseEntity<Page<TrainingPlannerResponseDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<TrainingPlanner> trainingPlanners = trainingPlannerService.findAll(page, size);
        return ResponseEntity.ok().body(trainingPlanners.map(trainingPlannerMapper::toDTO));
    }

    @GetMapping(value = "/me")
    public ResponseEntity<Page<TrainingPlannerResponseDTO>> findAllMe(@RequestParam int page, @RequestParam int size) {
        Page<TrainingPlanner> trainingPlanners = trainingPlannerService.findAllMe(page, size);
        return ResponseEntity.ok().body(trainingPlanners.map(trainingPlannerMapper::toDTO));
    }

    @GetMapping(value = "/{trainingPlannerId}")
    public ResponseEntity<TrainingPlannerResponseDTO> findById(@PathVariable UUID trainingPlannerId) {
        var trainingPlanner = trainingPlannerService.findById(trainingPlannerId);
        return ResponseEntity.ok().body(trainingPlannerMapper.toDTO(trainingPlanner));
    }

    @PostMapping
    public ResponseEntity<TrainingPlannerResponseDTO> createPlanner(@RequestBody TrainingPlannerRequestDTO body) {
        var trainingPlanner = trainingPlannerService.createPlanner(body);

        URI uri = ServletUriComponentsBuilder.fromPath("/{trainingPlannerId}")
                .buildAndExpand(trainingPlanner.getId())
                .toUri();

        return ResponseEntity.created(uri).body(trainingPlannerMapper.toDTO(trainingPlanner));
    }

    @PostMapping(value = "/{trainingPlannerId}/daytrainings")
    public ResponseEntity<DayTrainingResponseDTO> addDayTraining(@PathVariable UUID trainingPlannerId, @RequestBody DayTrainingRequestDTO body) {
        DayTraining dayTraining = trainingPlannerService.addDayTraining(trainingPlannerId, body);

        URI uri = ServletUriComponentsBuilder.fromPath("/{DayTrainingId}")
                .buildAndExpand(dayTraining.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dayTrainingMapper.toDTO(dayTraining));
    }

    @PutMapping(value = "/{trainingPlannerId}")
    public ResponseEntity<TrainingPlannerResponseDTO> updatePlanner(@PathVariable UUID trainingPlannerId, @RequestBody TrainingPlannerRequestDTO body) {
        var trainingPlanner = trainingPlannerService.updatePlanner(trainingPlannerId, body);
        return ResponseEntity.ok().body(trainingPlannerMapper.toDTO(trainingPlanner));
    }

    @PutMapping(value = "/{trainingPlannerId}/planner-status")
    public ResponseEntity<TrainingPlannerResponseDTO> updatePlannerStatus(@PathVariable UUID trainingPlannerId, @RequestBody TrainingPlannerStatusRequestDTO body) {
        var trainingPlanner = trainingPlannerService.updatePlannerStatus(trainingPlannerId, body);
        return ResponseEntity.ok().body(trainingPlannerMapper.toDTO(trainingPlanner));
    }

    @DeleteMapping(value = "/{trainingPlannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable UUID trainingPlannerId) {
        trainingPlannerService.deletePlanner(trainingPlannerId);
        return ResponseEntity.noContent().build();
    }
}