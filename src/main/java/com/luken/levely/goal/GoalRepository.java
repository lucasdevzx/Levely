package com.luken.levely.goal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    Optional<Page<Goal>> findAllByUserId(Pageable pageable, UUID userId);

    Optional<Page<Goal>> findAllByWorkoutId(Pageable pageable, UUID workoutId);

}
