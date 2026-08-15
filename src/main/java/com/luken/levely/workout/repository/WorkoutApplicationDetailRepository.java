package com.luken.levely.workout.repository;

import com.luken.levely.workout.model.WorkoutApplicationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WorkoutApplicationDetailRepository extends JpaRepository<WorkoutApplicationDetail, UUID> {
}