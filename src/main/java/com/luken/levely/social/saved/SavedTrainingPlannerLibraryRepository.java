package com.luken.levely.social.saved;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavedTrainingPlannerLibraryRepository extends JpaRepository<SavedTrainingPlannerLibrary, UUID> {

    boolean existsByTrainingPlannerLibraryIdAndSavedUserId(UUID trainingPlannerLibraryId, UUID userId);

    void deleteByTrainingPlannerLibraryIdAndSavedUserId(UUID trainingPlannerLibraryId, UUID userId);

}
