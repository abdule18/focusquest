package com.focusquest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.focusquest.enums.QuestStatus;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "quests")

public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "xp_reward", nullable = false)
    private int xpReward;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
