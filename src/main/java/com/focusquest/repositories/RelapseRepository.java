package com.focusquest.repositories;

import com.focusquest.model.RelapseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RelapseRepository extends JpaRepository<RelapseLog, UUID> {
}
