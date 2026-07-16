package com.luken.levely.controller;

import com.luken.levely.dto.response.TrainingPlannerLibraryResponseDTO;
import com.luken.levely.mapper.TrainingPlannerLibraryMapper;
import com.luken.levely.model.LikeTrainingPlannerLibrary;
import com.luken.levely.service.TrainingPlannerLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "trainingplannerlibraries")
@RequiredArgsConstructor
public class TrainingPlannerLibraryController {

    private final TrainingPlannerLibraryService trainingPlannerLibraryService;
    private final TrainingPlannerLibraryMapper trainingPlannerLibraryMapper;

    @PostMapping(value = "/{trainingPlannerLibraryId}/like")
    public ResponseEntity<TrainingPlannerLibraryResponseDTO> addLike(@PathVariable UUID trainingPlannerLibraryId) {
        var trainingPlannerLibrary = trainingPlannerLibraryService.addLike(trainingPlannerLibraryId);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{likeTrainingPlannerLibraryId")
                .buildAndExpand(
                        trainingPlannerLibrary
                                .getLikeTrainingPlannerLibraries()
                                .getLast()
                                .getId())
                .toUri();

        return ResponseEntity.created(uri).body(trainingPlannerLibraryMapper.toDTO(trainingPlannerLibrary));
    }

    @PostMapping(value = "/{trainingPlannerLibraryId}/saved")
    public ResponseEntity<TrainingPlannerLibraryResponseDTO> addSaved(@PathVariable UUID trainingPlannerLibraryId) {
        var trainingPlannerLibrary = trainingPlannerLibraryService.addSaved(trainingPlannerLibraryId);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{savedTrainingPlannerLibraryId")
                .buildAndExpand(
                        trainingPlannerLibrary
                                .getSavedTrainingPlannerLibraries()
                                .getLast()
                                .getId())
                .toUri();

        return ResponseEntity.created(uri).body(trainingPlannerLibraryMapper.toDTO(trainingPlannerLibrary));
    }

    @DeleteMapping(value = "/{trainingPlannerLibraryId}/like")
    public ResponseEntity<Void> deleteLike(@PathVariable UUID trainingPlannerLibraryId) {
        trainingPlannerLibraryService.deleteLikeTrainingPlannerLibrary(trainingPlannerLibraryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{trainingPlannerLibraryId}/saved")
    public ResponseEntity<Void> deleteSaved(@PathVariable UUID trainingPlannerLibraryId) {
        trainingPlannerLibraryService.deleteSavedTrainingPlannerLibrary(trainingPlannerLibraryId);
        return ResponseEntity.noContent().build();
    }

}