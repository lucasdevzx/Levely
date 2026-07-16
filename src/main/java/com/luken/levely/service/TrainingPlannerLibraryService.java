package com.luken.levely.service;

import com.luken.levely.model.Library;
import com.luken.levely.model.LikeTrainingPlannerLibrary;
import com.luken.levely.model.TrainingPlannerLibrary;
import com.luken.levely.repository.TrainingPlannerLibraryRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingPlannerLibraryService {

     private final TrainingPlannerLibraryRepository trainingPlannerLibraryRepository;

     private final TrainingPlannerService trainingPlannerService;
     private final LikeTrainingPlannerLibraryService likeTrainingPlannerLibraryService;
     private final AuthenticatedUser authenticatedUser;

     public TrainingPlannerLibrary findById(UUID trainingPlannerLibraryId) {
         return trainingPlannerLibraryRepository.findById(trainingPlannerLibraryId)
                 .orElseThrow(() -> new EntityNotFoundException(String.format("Entity training planner library not found by id: " + trainingPlannerLibraryId)));
     }

     public TrainingPlannerLibrary createTrainingPlannerLibrary(UUID trainingPlannerId, Library library) {
         var trainingPlanner = trainingPlannerService.findById(trainingPlannerId);
         authenticatedUser.ownershipValidator(trainingPlanner.getUser());

         var trainingPlannerLibrary = TrainingPlannerLibrary.create(trainingPlanner, library);
         return trainingPlannerLibraryRepository.save(trainingPlannerLibrary);
     }

     public TrainingPlannerLibrary addLike(UUID trainingPlannerLibraryId) {
         var trainingPlannerLibrary = findById(trainingPlannerLibraryId);
         authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

         var likeTrainingPlannerLibrary = likeTrainingPlannerLibraryService.createLikeTrainingPlannerLibrary(trainingPlannerLibrary);

         trainingPlannerLibrary.addLike(likeTrainingPlannerLibrary);
         return trainingPlannerLibraryRepository.save(trainingPlannerLibrary);
     }

     @Transactional
     public void deleteLikeTrainingPlannerLibrary(UUID trainingPlannerLibraryId) {
         var trainingPlannerLibrary = findById(trainingPlannerLibraryId);
         authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

         likeTrainingPlannerLibraryService.deleteLikeTrainingPlannerLibrary(trainingPlannerLibrary);
     }

}
