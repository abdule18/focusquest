package com.focusquest.dto.request;

import com.focusquest.enums.UrgeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrgeRequestDTO {

    @NotNull( message = "Urge level cannot be null")
    private UrgeStatus urgeLevel;
    @NotBlank( message = "Trigger cannot be blank")
    private String trigger;
    @NotBlank( message = "ActionTaken cannot be blank")
    private String actionTaken;
    @NotNull( message = "Resisted cannot be null")
    private Boolean resisted;
}
