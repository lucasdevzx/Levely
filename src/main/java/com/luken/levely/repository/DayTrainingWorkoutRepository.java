package com.luken.levely.repository;

import com.luken.levely.model.DayTrainingWorkout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DayTrainingWorkoutRepository extends JpaRepository<DayTrainingWorkout, UUID> {

    Page<DayTrainingWorkout> findAll(Pageable pageable);

}
