package com.luken.levely.service;

import com.luken.levely.model.*;
import com.luken.levely.repository.TrainingPlannerLibraryRepository;
import com.luken.levely.repository.TrainingPlannerRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingPlannerLibraryService {

    @PersistenceContext
    private EntityManager entityManager;

     private final TrainingPlannerLibraryRepository trainingPlannerLibraryRepository;

     private final TrainingPlannerService trainingPlannerService;
     private final TrainingPlannerRepository trainingPlannerRepository;

     private final DayTrainingService dayTrainingService;

     private final DayTrainingWorkoutService dayTrainingWorkoutService;

     private final WorkoutService workoutService;

     private final LikeTrainingPlannerLibraryService likeTrainingPlannerLibraryService;
     private final SavedTrainingPlannerLibraryService savedTrainingPlannerLibraryService;

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

     @Transactional
     public void importTrainingPlannerComplete(UUID trainingPlannerId) {
         var user = authenticatedUser.getAuthenticatedUser();
         var originalTrainingPlanner = trainingPlannerService.findById(trainingPlannerId);
         Set<Workout> resetWorkouts = Collections.newSetFromMap(new IdentityHashMap<>());
         entityManager.detach(originalTrainingPlanner);

         var originalDayTrainings = dayTrainingService.findAllByTrainingPlannerId(trainingPlannerId);
         for (DayTraining dayTraining : originalDayTrainings) {
             entityManager.detach(dayTraining);

             var originalDayTrainingWorkouts = dayTrainingWorkoutService.findAllByDayTrainingId(dayTraining.getId());
             for (DayTrainingWorkout dayTrainingWorkout : originalDayTrainingWorkouts) {
                 entityManager.detach(dayTrainingWorkout);

                 var workout = dayTrainingWorkout.getWorkout();
                 if (resetWorkouts.add(workout)) {
                     entityManager.detach(workout);
                     workout.importReset(user);
                 }

                 dayTrainingWorkout.importReset(dayTraining, workout);
             }
             dayTraining.importReset(originalTrainingPlanner, originalDayTrainingWorkouts);
         }

         originalTrainingPlanner.importReset(user, originalDayTrainings);
         trainingPlannerRepository.save(originalTrainingPlanner);
     }

     public TrainingPlannerLibrary addLike(UUID trainingPlannerLibraryId) {
         var trainingPlannerLibrary = findById(trainingPlannerLibraryId);
         authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

         var likeTrainingPlannerLibrary = likeTrainingPlannerLibraryService.createLikeTrainingPlannerLibrary(trainingPlannerLibrary);

         trainingPlannerLibrary.addLike(likeTrainingPlannerLibrary);
         return trainingPlannerLibraryRepository.save(trainingPlannerLibrary);
     }

     public TrainingPlannerLibrary addSaved(UUID trainingPlannerLibraryId) {
         var trainingPlannerLibrary = findById(trainingPlannerLibraryId);
         authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

         var savedTrainingPlannerLibrary = savedTrainingPlannerLibraryService.createSavedTrainingPlannerLibrary(trainingPlannerLibrary);

         trainingPlannerLibrary.addSaved(savedTrainingPlannerLibrary);
         return trainingPlannerLibraryRepository.save(trainingPlannerLibrary);
     }

     @Transactional
     public void deleteLikeTrainingPlannerLibrary(UUID trainingPlannerLibraryId) {
         var trainingPlannerLibrary = findById(trainingPlannerLibraryId);
         authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

         likeTrainingPlannerLibraryService.deleteLikeTrainingPlannerLibrary(trainingPlannerLibrary);
     }

     @Transactional
     public void deleteSavedTrainingPlannerLibrary(UUID trainingPlannerLibraryId) {
         var trainingPlannerLibrary = findById(trainingPlannerLibraryId);
         authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

         savedTrainingPlannerLibraryService.deleteSavedTrainingPlannerLibrary(trainingPlannerLibrary);
     }

}
