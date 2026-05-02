package com.focusquest.repositories;

import com.focusquest.model.UrgeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UrgeRepository extends JpaRepository<UrgeLog, UUID> {
}
