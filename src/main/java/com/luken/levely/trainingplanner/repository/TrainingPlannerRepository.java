package com.luken.levely.trainingplanner.repository;

import com.luken.levely.trainingplanner.model.TrainingPlanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingPlannerRepository extends JpaRepository<TrainingPlanner, UUID> {

    Page<TrainingPlanner> findAll(Pageable pageable);

    Optional<Page<TrainingPlanner>> findAllByUserId(UUID userId, Pageable pageable);

}
