package com.luken.levely.trainingplanner.repository;

import com.luken.levely.trainingplanner.model.TrainingPlannerLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingPlannerLibraryRepository extends JpaRepository<TrainingPlannerLibrary, UUID> {
}
