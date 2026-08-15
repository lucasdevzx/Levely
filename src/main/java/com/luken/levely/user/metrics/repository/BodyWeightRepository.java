package com.luken.levely.user.metrics.repository;

import com.luken.levely.user.metrics.model.BodyStats;
import com.luken.levely.user.metrics.model.BodyWeight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BodyWeightRepository extends JpaRepository<BodyWeight, UUID> {

    Optional<Page<BodyWeight>> findAllByUserId(Pageable pageable, UUID userId);

}
