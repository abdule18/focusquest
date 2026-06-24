package com.focusquest.service;

import com.focusquest.dto.response.DashboardResponseDTO;
import com.focusquest.enums.QuestStatus;
import com.focusquest.model.UrgeLog;
import com.focusquest.repositories.CheckInRepository;
import com.focusquest.repositories.QuestRepository;
import com.focusquest.repositories.RelapseRepository;
import com.focusquest.repositories.UrgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DashboardService {

    private final QuestRepository questRepository;
    private final CheckInRepository checkInRepository;
    private final UrgeRepository urgeRepository;
    private final RelapseRepository relapseRepository;

    public DashboardResponseDTO getDashboardStats(){
        long totalQuests = questRepository.count();
        long totalCheckIns = checkInRepository.count();
        long totalUrges = urgeRepository.count();
        long totalRelapses = relapseRepository.count();

        long completedQuests  =  questRepository.countByStatus(QuestStatus.COMPLETED);
        long resistedUrges = urgeRepository.countByResisted(true);
        List<UrgeLog> urges = urgeRepository.findAll();

        Map<String, Integer> triggerCounts = new HashMap<>();

        for (UrgeLog urge : urges) {
            String trigger = urge.getTrigger();

            if (triggerCounts.containsKey(trigger)) {
                int oldCount = triggerCounts.get(trigger);
                triggerCounts.put(trigger, oldCount + 1);
            } else {
                triggerCounts.put(trigger, 1);
            }
        }

        long score = 100 - (totalRelapses * 10) + (resistedUrges * 2);

        if (score > 100) {
            score = 100;
        }

        if (score < 0) {
            score = 0;
        }

        int recoveryScore = (int) score;

        return DashboardResponseDTO.builder()
                .totalQuests(totalQuests)
                .completedQuests(completedQuests)
                .totalCheckIns(totalCheckIns)
                .totalUrges(totalUrges)
                .resistedUrges(resistedUrges)
                .totalRelapses(totalRelapses)
                .recoveryScore(recoveryScore)
                .triggerCounts(triggerCounts)
                .build();
    }
}
