package com.focusquest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.focusquest.enums.UrgeStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrgeResponseDTO {

    private UUID id;
    private UrgeStatus urgeLevel;
    private String trigger;
    private String actionTaken;
    private boolean resisted;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime createdAt;
}
