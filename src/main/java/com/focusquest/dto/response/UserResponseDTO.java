package com.focusquest.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private UUID id;
    private String email;
    private String token;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a")
    private LocalDateTime createdAt;
}
