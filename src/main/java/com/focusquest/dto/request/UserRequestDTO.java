package com.focusquest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank( message = "Email cannot be blank")
    private String email;

    @NotBlank( message = "Password cannot be blank")
    private String password;
}
