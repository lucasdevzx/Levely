package com.luken.levely.repository;

import com.luken.levely.model.DayTrainingWorkoutLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DayTrainingWorkoutLogRepository extends JpaRepository<DayTrainingWorkoutLog, UUID> {

    Page<DayTrainingWorkoutLog> findAll(Pageable pageable);

}
