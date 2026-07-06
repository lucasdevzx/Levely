package com.luken.levely.controller;

import com.luken.levely.dto.request.WorkoutRequestDTO;
import com.luken.levely.dto.response.WorkoutResponseDTO;
import com.luken.levely.mapper.WorkoutMapper;
import com.luken.levely.model.Workout;
import com.luken.levely.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;

    @GetMapping
    public ResponseEntity<Page<WorkoutResponseDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<Workout> workouts = workoutService.findAll(page, size);
        return ResponseEntity.ok().body(workouts.map(workoutMapper::toDTO));
    }

    @GetMapping(value = "/{workoutId}")
    public ResponseEntity<WorkoutResponseDTO> findById(@PathVariable UUID workoutId) {
        var workout = workoutService.findById(workoutId);
        return ResponseEntity.ok().body(workoutMapper.toDTO(workout));
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> createWorkout(@RequestBody WorkoutRequestDTO body) {
        var workout = workoutService.createWorkout(body);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{workoutId}")
                .buildAndExpand(workout.getId())
                .toUri();

        return ResponseEntity.created(uri).body(workoutMapper.toDTO(workout));
    }

    @PutMapping(value = "/{workoutId}")
    public ResponseEntity<WorkoutResponseDTO> updateWorkout(@PathVariable UUID workoutId, @RequestBody WorkoutRequestDTO body) {
        var workout = workoutService.updateWorkout(workoutId, body);
        return ResponseEntity.ok().body(workoutMapper.toDTO(workout));
    }

    @DeleteMapping(value = "/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID workoutId) {
        workoutService.deleteWorkout(workoutId);
        return ResponseEntity.noContent().build();
    }
}
