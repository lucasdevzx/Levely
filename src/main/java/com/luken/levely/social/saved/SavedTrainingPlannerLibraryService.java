package com.luken.levely.social.saved;

import com.luken.levely.trainingplanner.model.TrainingPlannerLibrary;
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

        boolean existsSaved = savedTrainingPlannerLibraryRepository
                .existsByTrainingPlannerLibraryIdAndSavedUserId(trainingPlannerLibrary.getId(), user.getId());

        var saved = savedService.createSaved(user, existsSaved);
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
