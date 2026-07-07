package com.luken.levely.controller;

import com.luken.levely.dto.request.DayTrainingWorkoutRequestDTO;
import com.luken.levely.dto.response.DayTrainingResponseDTO;
import com.luken.levely.dto.response.DayTrainingWorkoutResponseDTO;
import com.luken.levely.mapper.DayTrainingWorkoutMapper;
import com.luken.levely.model.DayTrainingWorkout;
import com.luken.levely.service.DayTrainingWorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/daytrainingworkouts")
@RequiredArgsConstructor
public class DayTrainingWorkoutController {

    private final DayTrainingWorkoutService dayTrainingWorkoutService;
    private final DayTrainingWorkoutMapper dayTrainingWorkoutMapper;

    @GetMapping(value = "/{dayTrainingWorkoutId}")
    public ResponseEntity<DayTrainingWorkoutResponseDTO> findById(@PathVariable UUID dayTrainingWorkoutId) {
        var dayTrainingWorkout = dayTrainingWorkoutService.findById(dayTrainingWorkoutId);
        return ResponseEntity.ok().body(dayTrainingWorkoutMapper.toDTO(dayTrainingWorkout));
    }

    @GetMapping
    public ResponseEntity<Page<DayTrainingWorkoutResponseDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<DayTrainingWorkout> dayTrainingWorkouts = dayTrainingWorkoutService.findAll(page, size);
        return ResponseEntity.ok().body(dayTrainingWorkouts.map(dayTrainingWorkoutMapper::toDTO));
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