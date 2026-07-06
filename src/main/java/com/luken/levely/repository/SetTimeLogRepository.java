package com.luken.levely.repository;

import com.luken.levely.model.SetRepLog;
import com.luken.levely.model.SetTimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SetTimeLogRepository extends JpaRepository<SetTimeLog, UUID> {
}