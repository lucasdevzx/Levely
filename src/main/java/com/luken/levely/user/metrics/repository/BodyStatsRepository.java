package com.luken.levely.user.metrics.repository;

import com.luken.levely.user.metrics.model.BodyStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BodyStatsRepository extends JpaRepository<BodyStats, UUID> {

    Optional<Page<BodyStats>> findAllByUserId(Pageable pageable, UUID userId);

}
