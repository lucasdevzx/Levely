package com.luken.levely.repository;

import com.luken.levely.model.Saved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavedRepository extends JpaRepository<Saved, UUID> {
}
