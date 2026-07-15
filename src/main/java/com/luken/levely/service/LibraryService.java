package com.luken.levely.service;

import com.luken.levely.model.Library;
import com.luken.levely.model.TrainingPlannerLibrary;
import com.luken.levely.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    private final TrainingPlannerLibraryService trainingPlannerLibraryService;

    public Library findById(UUID libraryId) {
        return libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Entity library not found by id: " + libraryId)));
    }

    public Library createLibrary() {
        var library = Library.create();
        return libraryRepository.save(library);
    }

    public TrainingPlannerLibrary addTrainingPlanner(UUID trainingPlannerId, UUID libraryId) {
        var library = findById(libraryId);
        var trainingPlannerLibrary = trainingPlannerLibraryService.createTrainingPlannerLibrary(trainingPlannerId, library);
        library.addTrainingPlanner(trainingPlannerLibrary);
        return trainingPlannerLibrary;
    }

}
