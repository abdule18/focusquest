package com.focusquest.repositories;

import com.focusquest.enums.QuestStatus;
import com.focusquest.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestRepository extends JpaRepository<Quest, UUID> {
    long countByStatus(QuestStatus status);
}
