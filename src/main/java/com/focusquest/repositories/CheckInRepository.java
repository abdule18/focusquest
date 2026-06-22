package com.focusquest.repositories;

import com.focusquest.model.DailyCheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CheckInRepository extends JpaRepository<DailyCheckIn, UUID> {
    List<DailyCheckIn> findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(
            LocalDateTime start,
            LocalDateTime end
    );
}
