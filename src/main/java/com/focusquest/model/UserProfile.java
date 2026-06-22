package com.focusquest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "current_streak", nullable = false)
    private int currentStreak;

    @Column(name = "longest_streak", nullable = false)
    private int longestStreak;

    @Column(name = "total_xp", nullable = false)
    private int totalXp;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "recovery_score", nullable = false)
    private int recoveryScore;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
