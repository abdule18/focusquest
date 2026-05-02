package com.focusquest.model;

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
@Table(name = "urge_log")
public class UrgeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "urge_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private UrgeStatus urgeLevel;

    @Column(name = "trigger", nullable = false)
    private String trigger;

    @Column(name = "action_taken", nullable = false)
    private String actionTaken;

    @Column(name = "resisted", nullable = false)
    private boolean resisted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
