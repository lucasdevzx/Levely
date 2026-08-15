package com.luken.levely.daytraining.controller;

import com.luken.levely.daytraining.dto.DayTrainingRequestDTO;
import com.luken.levely.daytraining.dto.DayTrainingResponseDTO;
import com.luken.levely.daytraining.mapper.DayTrainingMapper;
import com.luken.levely.daytraining.model.DayTraining;
import com.luken.levely.daytraining.service.DayTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/daytrainings")
@RequiredArgsConstructor
public class DayTrainingController {

    private final DayTrainingService dayTrainingService;
    private final DayTrainingMapper dayTrainingMapper;

    @GetMapping
    public ResponseEntity<Page<DayTrainingResponseDTO>> findAll(@RequestParam int page, @RequestParam int size) {
        Page<DayTraining> dayTrainings = dayTrainingService.findAll(page, size);
        return ResponseEntity.ok().body(dayTrainings.map(dayTrainingMapper::toDTO));
    }

    @GetMapping(value = "/{dayTrainingId}")
    public ResponseEntity<DayTrainingResponseDTO> findById(@PathVariable UUID dayTrainingId) {
        var dayTraining = dayTrainingService.findById(dayTrainingId);
        return ResponseEntity.ok().body(dayTrainingMapper.toDTO(dayTraining));
    }

    @PutMapping(value = "/{dayTrainingId}")
    public ResponseEntity<DayTrainingResponseDTO> updateDayTraining(@PathVariable UUID dayTrainingId, @RequestBody DayTrainingRequestDTO body) {
        var dayTraining = dayTrainingService.updateDayTraining(dayTrainingId, body);
        return ResponseEntity.ok().body(dayTrainingMapper.toDTO(dayTraining));

    }

    @DeleteMapping(value = "/{dayTrainingId}")
    public ResponseEntity<Void> deleteDayTraining(@PathVariable UUID dayTrainingId) {
        dayTrainingService.deleteDayTraining(dayTrainingId);
        return ResponseEntity.noContent().build();
    }
}