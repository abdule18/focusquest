package com.focusquest.dto.response;

import com.focusquest.enums.MoodLevel;
import com.focusquest.enums.UrgeStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInResponseDTO {

    private UUID id;
    private MoodLevel moodLevel;
    private UrgeStatus urgeLevel;
    private boolean hadUrge;
    private boolean relapsed;
    private String reflection;
    private LocalDateTime createdAt;
}
