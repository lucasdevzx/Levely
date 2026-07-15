package com.luken.levely.controller;

import com.luken.levely.dto.response.LibraryResponseDTO;
import com.luken.levely.dto.response.TrainingPlannerLibraryResponseDTO;
import com.luken.levely.mapper.LibraryMapper;
import com.luken.levely.mapper.TrainingPlannerLibraryMapper;
import com.luken.levely.model.Library;
import com.luken.levely.model.TrainingPlannerLibrary;
import com.luken.levely.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/libraries")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;
    private final LibraryMapper libraryMapper;

    private final TrainingPlannerLibraryMapper trainingPlannerLibraryMapper;

    // Create response DTO
    @GetMapping(value = "/{libraryId}")
    public ResponseEntity<LibraryResponseDTO> findById(@PathVariable UUID libraryId) {
        var library = libraryService.findById(libraryId);
        return ResponseEntity.ok().body(libraryMapper.toDTO(library));
    }

    @PostMapping
    public ResponseEntity<LibraryResponseDTO> createLibrary() {
        var library = libraryService.createLibrary();

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{libraryId}")
                .buildAndExpand(library.getId())
                .toUri();

        return ResponseEntity.created(uri).body(libraryMapper.toDTO(library));
    }

    @PostMapping(value = "/{libraryId}/trainingplanner/{trainingPlannerId}")
    public ResponseEntity<TrainingPlannerLibraryResponseDTO> addTrainingPlanner(@PathVariable UUID trainingPlannerId, @PathVariable UUID libraryId) {
        var trainingPlannerLibrary = libraryService.addTrainingPlanner(trainingPlannerId, libraryId);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{trainingPlannerLibrary}")
                .buildAndExpand(trainingPlannerLibrary.getId())
                .toUri();

        return ResponseEntity.created(uri).body(trainingPlannerLibraryMapper.toDTO(trainingPlannerLibrary));
    }

}