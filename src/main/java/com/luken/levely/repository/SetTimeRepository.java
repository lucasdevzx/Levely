package com.luken.levely.repository;

import com.luken.levely.model.SetTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SetTimeRepository extends JpaRepository<SetTime, UUID> {
}