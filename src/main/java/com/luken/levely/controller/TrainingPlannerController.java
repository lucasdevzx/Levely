package com.luken.levely.controller;

import com.luken.levely.dto.request.DayTrainingDefaultRequestDTO;
import com.luken.levely.dto.response.DayTrainingResponseDTO;
import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.dto.response.TrainingPlannerResponseDTO;
import com.luken.levely.mapper.DayTrainingMapper;
import com.luken.levely.mapper.TrainingPlannerMapper;
import com.luken.levely.model.DayTraining;
import com.luken.levely.model.TrainingPlanner;
import com.luken.levely.service.TrainingPlannerService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<TrainingPlannerResponseDTO> createPlanner(@RequestBody TrainingPlannerRequestDTO body) {
        TrainingPlanner trainingPlanner = trainingPlannerService.createPlanner(body);

        URI uri = ServletUriComponentsBuilder.fromPath("/{trainingPlannerId}")
                .buildAndExpand(trainingPlanner.getId())
                .toUri();

        return ResponseEntity.created(uri).body(trainingPlannerMapper.toDTO(trainingPlanner));
    }

    @PostMapping(value = "/{trainingPlannerId}/day-trainings")
    public ResponseEntity<DayTrainingResponseDTO> addDayTraining(@PathVariable UUID trainingPlannerId, @RequestBody DayTrainingDefaultRequestDTO body) {
        DayTraining dayTraining = trainingPlannerService.addDayTraining(trainingPlannerId, body);

        URI uri = ServletUriComponentsBuilder.fromPath("/{DayTrainingId}")
                .buildAndExpand(dayTraining.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dayTrainingMapper.toDTO(dayTraining));
    }

}