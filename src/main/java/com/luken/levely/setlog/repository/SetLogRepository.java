package com.luken.levely.setlog.repository;

import com.luken.levely.setlog.model.SetLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SetLogRepository extends JpaRepository<SetLog, UUID> {
}
