package com.focusquest.dto.response;

import com.focusquest.enums.QuestStatus;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestResponseDTO {

    private UUID id;
    private String title;
    private String description;
    private int xpReward;
    private QuestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
}
