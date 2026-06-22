package com.focusquest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.focusquest.enums.MoodLevel;
import com.focusquest.enums.UrgeStatus;
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
@Table(name = "daily_check_in")
public class DailyCheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "mood_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodLevel moodLevel;

    @Column(name = "urge_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private UrgeStatus urgeLevel;

    @Column(name = "had_urge", nullable = false)
    private boolean hadUrge;

    @Column(name = "relapsed", nullable = false)
    private boolean relapsed;

    @Column(name = "reflection", nullable = false)
    private String reflection;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
