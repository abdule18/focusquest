package com.focusquest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RelapseRequestDTO {

    @NotBlank(message = "Trigger is required")
    private String trigger;
    @NotBlank(message = "What happened is required")
    private String whatHappened;
    @NotBlank(message = "Lesson learned is required")
    private String lessonLearned;
    @NotBlank(message = "Prevention plan is required")
    private String preventionPlan;
}
