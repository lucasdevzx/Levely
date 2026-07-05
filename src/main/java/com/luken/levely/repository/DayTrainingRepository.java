package com.luken.levely.repository;

import com.luken.levely.model.DayTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DayTrainingRepository extends JpaRepository<DayTraining, UUID> {

    Page<DayTraining> findAll(Pageable pageable);

}