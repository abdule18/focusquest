package com.focusquest.dto.request;

import com.focusquest.enums.MoodLevel;
import com.focusquest.enums.UrgeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInRequestDTO {

    @NotNull( message = "Mood level cannot be null")
    private MoodLevel moodLevel;

    @NotNull( message = "Urge level cannot be null")
    private UrgeStatus urgeLevel;

    @NotNull(message = "Had urge cannot be null")
    private Boolean hadUrge;

    @NotNull(message = "Relapsed cannot be null")
    private Boolean relapsed;

    @NotBlank( message = "Reflection cannot be blank")
    private String reflection;
}
