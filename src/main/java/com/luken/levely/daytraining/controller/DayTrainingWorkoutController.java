package com.luken.levely.daytraining.controller;

import com.luken.levely.daytraining.dto.DayTrainingWorkoutRequestDTO;
import com.luken.levely.daytraining.dto.DayTrainingWorkoutResponseDTO;
import com.luken.levely.daytraining.mapper.DayTrainingWorkoutMapper;
import com.luken.levely.daytraining.model.DayTrainingWorkout;
import com.luken.levely.daytraining.service.DayTrainingWorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/daytrainingworkouts")
@RequiredArgsConstructor
public class DayTrainingWorkoutController {

    private final DayTrainingWorkoutService dayTrainingWorkoutService;
    private final DayTrainingWorkoutMapper dayTrainingWorkoutMapper;

    @GetMapping
    public ResponseEntity<Page<DayTrainingWorkoutResponseDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<DayTrainingWorkout> dayTrainingWorkouts = dayTrainingWorkoutService.findAll(page, size);
        return ResponseEntity.ok().body(dayTrainingWorkouts.map(dayTrainingWorkoutMapper::toDTO));
    }

    @GetMapping(value = "/daytraining/{dayTrainingId}")
    public ResponseEntity<List<DayTrainingWorkoutResponseDTO>> findAllByDayTrainingId(@PathVariable UUID dayTrainingId) {
        var dayTrainingWorkouts = dayTrainingWorkoutService.findAllByDayTrainingId(dayTrainingId);
        return ResponseEntity.ok().body(dayTrainingWorkouts.stream().map(dayTrainingWorkoutMapper::toDTO).toList());
    }

    @GetMapping(value = "/{dayTrainingWorkoutId}")
    public ResponseEntity<DayTrainingWorkoutResponseDTO> findById(@PathVariable UUID dayTrainingWorkoutId) {
        var dayTrainingWorkout = dayTrainingWorkoutService.findById(dayTrainingWorkoutId);
        return ResponseEntity.ok().body(dayTrainingWorkoutMapper.toDTO(dayTrainingWorkout));
    }

    @PostMapping(value = "/{dayTrainingId}/{workoutId}")
    public ResponseEntity<DayTrainingWorkoutResponseDTO> createDayTrainingWorkout(
            @PathVariable UUID dayTrainingId,
            @PathVariable UUID workoutId,
            @RequestBody DayTrainingWorkoutRequestDTO body) {

        var dayTrainingWorkout = dayTrainingWorkoutService.createDayTrainingWorkout(dayTrainingId, workoutId, body);
        return ResponseEntity.ok().body(dayTrainingWorkoutMapper.toDTO(dayTrainingWorkout));
    }

    @PutMapping(value = "/{dayTrainingWorkoutId}")
    public ResponseEntity<DayTrainingWorkoutResponseDTO> updateDayTrainingWorkout(@PathVariable UUID dayTrainingWorkoutId,
                                                                                  @RequestBody DayTrainingWorkoutRequestDTO body) {

        var dayTrainingWorkout = dayTrainingWorkoutService.updateDayTrainingWorkout(dayTrainingWorkoutId, body);
        return ResponseEntity.ok().body(dayTrainingWorkoutMapper.toDTO(dayTrainingWorkout));
    }

    @DeleteMapping(value = "/{dayTrainingWorkoutId}")
    public ResponseEntity<Void> deleteDayTrainingWorkout(@PathVariable UUID dayTrainingWorkoutId) {
        dayTrainingWorkoutService.deleteDayTrainingWorkout(dayTrainingWorkoutId);
        return ResponseEntity.noContent().build();
    }
}