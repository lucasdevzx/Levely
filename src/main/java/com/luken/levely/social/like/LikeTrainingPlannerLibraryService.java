package com.luken.levely.social.like;

import com.luken.levely.trainingplanner.model.TrainingPlannerLibrary;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeTrainingPlannerLibraryService {

    private final LikeTrainingPlannerLibraryRepository likeTrainingPlannerLibraryRepository;

    private final LikeService likeService;
    private final AuthenticatedUser authenticatedUser;

    public LikeTrainingPlannerLibrary createLikeTrainingPlannerLibrary(TrainingPlannerLibrary trainingPlannerLibrary) {
        var user = authenticatedUser.getAuthenticatedUser();

        boolean existsLike = likeTrainingPlannerLibraryRepository
                .existsByTrainingPlannerLibraryIdAndLikeUserId(trainingPlannerLibrary.getId(), user.getId());

        var like = likeService.createLike(user, existsLike);
        var likeTrainingPlannerLibrary = LikeTrainingPlannerLibrary.create(like, trainingPlannerLibrary);
        return likeTrainingPlannerLibraryRepository.save(likeTrainingPlannerLibrary);
    }

    @Transactional
    public void deleteLikeTrainingPlannerLibrary(TrainingPlannerLibrary trainingPlannerLibrary) {
        authenticatedUser.ownershipValidator(trainingPlannerLibrary.getTrainingPlanner().getUser());

        var user = authenticatedUser.getAuthenticatedUser();
        likeTrainingPlannerLibraryRepository.deleteByTrainingPlannerLibraryIdAndLikeUserId(trainingPlannerLibrary.getId(), user.getId());
    }

}
