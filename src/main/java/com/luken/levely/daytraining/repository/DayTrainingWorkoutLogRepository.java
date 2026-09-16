package com.luken.levely.daytraining.repository;

import com.luken.levely.daytraining.model.DayTrainingWorkoutLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayTrainingWorkoutLogRepository extends JpaRepository<DayTrainingWorkoutLog, UUID> {

    Page<DayTrainingWorkoutLog> findAll(Pageable pageable);

    Optional<List<DayTrainingWorkoutLog>> findAllByCompletedTrueOrderByCreatedAtDesc();

    Optional<List<DayTrainingWorkoutLog>> findAllByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    Optional<DayTrainingWorkoutLog> findByDayTrainingWorkoutId(UUID dayTrainingWorkoutId);

}
