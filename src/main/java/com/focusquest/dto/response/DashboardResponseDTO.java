package com.focusquest.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDTO {
    private long totalQuests;
    private long completedQuests;
    private long totalCheckIns;
    private long totalUrges;
    private long resistedUrges;
    private long totalRelapses;
    private int recoveryScore;
}
