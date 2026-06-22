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
@Table(name = "relapse_log")
public class RelapseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "trigger", nullable = false)
    private String trigger;

    @Column(name = "what_happened", nullable = false)
    private String whatHappened;

    @Column(name = "lesson_learned", nullable = false)
    private String lessonLearned;

    @Column(name = "prevention_plan", nullable = false)
    private String preventionPlan;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime createdAt;
}
