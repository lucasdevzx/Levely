package com.luken.levely.workout.repository;

import com.luken.levely.workout.model.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, UUID> {

    Page<Workout> findAll(Pageable pageable);

    Optional<List<Workout>> findAllByDayTrainingWorkoutsId(UUID dayTrainingWorkoutId);

}
