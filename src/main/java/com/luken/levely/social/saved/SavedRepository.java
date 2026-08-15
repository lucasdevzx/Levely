package com.luken.levely.social.saved;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavedRepository extends JpaRepository<Saved, UUID> {
}
