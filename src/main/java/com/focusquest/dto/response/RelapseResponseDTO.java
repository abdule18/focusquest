package com.focusquest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelapseResponseDTO {

    private UUID id;
    private String trigger;
    private String whatHappened;
    private String lessonLearned;
    private String preventionPlan;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime createdAt;
}
