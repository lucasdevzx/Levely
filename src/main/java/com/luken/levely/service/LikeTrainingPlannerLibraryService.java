package com.luken.levely.service;

import com.luken.levely.model.LikeTrainingPlannerLibrary;
import com.luken.levely.model.TrainingPlannerLibrary;
import com.luken.levely.repository.LikeTrainingPlannerLibraryRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeTrainingPlannerLibraryService {

    private final LikeTrainingPlannerLibraryRepository likeTrainingPlannerLibraryRepository;

    private final LikeService likeService;
    private final AuthenticatedUser authenticatedUser;

    public LikeTrainingPlannerLibrary createLikeTrainingPlannerLibrary(TrainingPlannerLibrary trainingPlannerLibrary) {
        var user = authenticatedUser.getAuthenticatedUser();

        if (likeTrainingPlannerLibraryRepository.existsByTrainingPlannerLibraryIdAndLikeUserId(trainingPlannerLibrary.getId(), user.getId())) {
            throw new IllegalArgumentException("You can only like it once");
        }

        var like = likeService.createLike(user);
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
