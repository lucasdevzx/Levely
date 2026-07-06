package com.luken.levely.repository;

import com.luken.levely.model.SetLog;
import com.luken.levely.model.SetRepLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SetLogRepository extends JpaRepository<SetLog, UUID> {
}
