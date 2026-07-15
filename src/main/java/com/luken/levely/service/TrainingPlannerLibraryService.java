package com.luken.levely.service;

import com.luken.levely.model.Library;
import com.luken.levely.model.TrainingPlannerLibrary;
import com.luken.levely.repository.TrainingPlannerLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingPlannerLibraryService {

     private final TrainingPlannerLibraryRepository trainingPlannerLibraryRepository;

     private final TrainingPlannerService trainingPlannerService;

     public TrainingPlannerLibrary createTrainingPlannerLibrary(UUID trainingPlannerId, Library library) {
         var trainingPlanner = trainingPlannerService.findById(trainingPlannerId);

         var trainingPlannerLibrary = TrainingPlannerLibrary.create(trainingPlanner, library);
         return trainingPlannerLibraryRepository.save(trainingPlannerLibrary);
     }

}
