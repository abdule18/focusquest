package com.focusquest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RelapseRequestDTO {

    @NotBlank(message = "trigger is required")
    private String trigger;
    @NotBlank(message = "what happened is required")
    private String whatHappened;
    @NotBlank(message = "lesson learned is required")
    private String lessonLearned;
    @NotBlank(message = "prevention plan is required")
    private String preventionPlan;
}
