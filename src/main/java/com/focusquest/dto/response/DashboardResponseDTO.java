package com.focusquest.dto.response;

import lombok.*;

import java.util.Map;

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
    private Map<String, Integer> triggerCounts;
}
