package com.luken.levely.repository;

import com.luken.levely.model.LikeTrainingPlannerLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeTrainingPlannerLibraryRepository extends JpaRepository<LikeTrainingPlannerLibrary, UUID> {

    boolean existsByTrainingPlannerLibraryIdAndLikeUserId(UUID trainingPlannerLibraryId, UUID userId);

    void deleteByTrainingPlannerLibraryIdAndLikeUserId(UUID trainingPlannerLibraryId, UUID userId);

}
