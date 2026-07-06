package com.luken.levely.repository;

import com.luken.levely.model.SetRepLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SetRepLogRepository extends JpaRepository<SetRepLog, UUID> {
}