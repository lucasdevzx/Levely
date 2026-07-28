package com.luken.levely.controller;

import com.luken.levely.dto.request.GoalRequestDTO;
import com.luken.levely.dto.response.GoalResponseDTO;
import com.luken.levely.mapper.GoalMapper;
import com.luken.levely.model.Goal;
import com.luken.levely.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;
    private final GoalMapper goalMapper;

    @GetMapping(value = "/me")
    public ResponseEntity<Page<GoalResponseDTO>> findAllMe(@RequestParam int page, @RequestParam int size) {
        Page<Goal> goals = goalService.findAllMe(page, size);
        return ResponseEntity.ok().body(goals.map(goalMapper::toDTO));
    }

    @GetMapping(value = "/{workoutId}/workout")
    public ResponseEntity<Page<GoalResponseDTO>> findAllByWorkoutId(@RequestParam int page, @RequestParam int size, @PathVariable UUID workoutId) {
        Page<Goal> goals = goalService.findAllByWorkoutId(page, size, workoutId);
        return ResponseEntity.ok().body(goals.map(goalMapper::toDTO));
    }

    @GetMapping(value = "/{goalId}")
    public ResponseEntity<GoalResponseDTO> findById(@PathVariable UUID goalId) {
        var goal = goalService.findById(goalId);
        return ResponseEntity.ok().body(goalMapper.toDTO(goal));
    }

    @PostMapping(value = "/{workoutId}")
    public ResponseEntity<GoalResponseDTO> createGoal(@RequestBody GoalRequestDTO body, @PathVariable UUID workoutId) {
        var goal = goalService.createGoal(body, workoutId);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{goalId}")
                .buildAndExpand(goal.getId())
                .toUri();

        return ResponseEntity.created(uri).body(goalMapper.toDTO(goal));
    }

    @PutMapping(value = "/{goalId}")
    public ResponseEntity<GoalResponseDTO> updateGoal(@RequestBody GoalRequestDTO body, @PathVariable UUID goalId) {
        var goal = goalService.updateGoal(body, goalId);
        return ResponseEntity.ok().body(goalMapper.toDTO(goal));

    }

    @DeleteMapping(value = "/{workoutId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable UUID workoutId) {
        goalService.deleteGoal(workoutId);
        return ResponseEntity.noContent().build();
    }

}
