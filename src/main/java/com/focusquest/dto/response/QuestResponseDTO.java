package com.focusquest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime completedAt;
}
