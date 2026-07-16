package com.luken.levely.service;

import com.luken.levely.model.SavedTrainingPlannerLibrary;
import com.luken.levely.model.TrainingPlanner;
import com.luken.levely.model.TrainingPlannerLibrary;
import com.luken.levely.repository.SavedTrainingPlannerLibraryRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedTrainingPlannerLibraryService {

    private final SavedTrainingPlannerLibraryRepository savedTrainingPlannerLibraryRepository;

    private final AuthenticatedUser authenticatedUser;
    private final SavedService savedService;

    public SavedTrainingPlannerLibrary createSavedTrainingPlannerLibrary(TrainingPlannerLibrary trainingPlannerLibrary) {
        var user = authenticatedUser.getAuthenticatedUser();

        if (savedTrainingPlannerLibraryRepository.existsByTrainingPlannerLibraryIdAndSavedUserId(trainingPlannerLibrary.getId(), user.getId())) {
            throw new IllegalArgumentException("You can only save it once");
        }

        var saved = savedService.createSaved(user);
        var savedTrainingPlannerLibrary = SavedTrainingPlannerLibrary.create(saved, trainingPlannerLibrary);
        return savedTrainingPlannerLibraryRepository.save(savedTrainingPlannerLibrary);
    }

    @Transactional
    public void deleteSavedTrainingPlannerLibrary(TrainingPlannerLibrary trainingPlannerLibrary) {
        authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

        var user = authenticatedUser.getAuthenticatedUser();
        savedTrainingPlannerLibraryRepository.deleteByTrainingPlannerLibraryIdAndSavedUserId(trainingPlannerLibrary.getId(), user.getId());
    }
}
