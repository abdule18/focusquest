package com.focusquest.service;

import com.focusquest.dto.request.QuestRequestDTO;
import com.focusquest.dto.response.QuestResponseDTO;
import com.focusquest.enums.QuestStatus;
import com.focusquest.exception.ResourceNotFoundException;
import com.focusquest.model.Quest;
import com.focusquest.repositories.QuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class QuestService {

    private final QuestRepository questRepository;
    private final Deque<UUID> recentlyCompletedQuestIds = new ArrayDeque<>();
    private final  Deque<String> longestWaitedQuest = new ArrayDeque<>();

    public QuestResponseDTO createQuest(QuestRequestDTO request) {

        LocalDateTime now = LocalDateTime.now();

        Quest quest = Quest.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .xpReward(request.getXpReward())
                .status(QuestStatus.NOT_STARTED)
                .createdAt(now)
                .updatedAt(now)
                .completedAt(null)
                .build();

        Quest saveQuest = questRepository.save(quest);

        return mapToQuestResponseDTO(saveQuest);
    }

    public List<QuestResponseDTO> getAllQuests() {
        List<Quest> quests = questRepository.findAll();

        return quests
                .stream()
                .map(this::mapToQuestResponseDTO)
                .toList();
    }

    public QuestResponseDTO getQuestById(UUID id) {

        Quest quest = findQuestById(id);

        return mapToQuestResponseDTO(quest);
    }

    public QuestResponseDTO updateQuestById(UUID id, QuestRequestDTO request) {

        Quest quest = findQuestById(id);

        quest.setTitle(request.getTitle());
        quest.setDescription(request.getDescription());
        quest.setXpReward(request.getXpReward());
        quest.setUpdatedAt(LocalDateTime.now());

        Quest savedQuest = questRepository.save(quest);

        return mapToQuestResponseDTO(savedQuest);
    }

    public QuestResponseDTO undoLastQuest() {

        if (recentlyCompletedQuestIds.isEmpty()) {
            throw new ResourceNotFoundException("No quest completed");
        }
        UUID undone = recentlyCompletedQuestIds.pop();

        Quest quest = findQuestById(undone);

        quest.setStatus(QuestStatus.NOT_STARTED);
        quest.setCompletedAt(null);

        Quest savedQuest = questRepository.save(quest);

        return mapToQuestResponseDTO(savedQuest);
    }

    public QuestResponseDTO completeQuest(UUID id) {

        LocalDateTime now = LocalDateTime.now();

        Quest quest = findQuestById(id);

        quest.setStatus(QuestStatus.COMPLETED);
        quest.setCompletedAt(now);
        quest.setUpdatedAt(now);

        Quest saveQuest = questRepository.save(quest);

        recentlyCompletedQuestIds.push(id);

        return mapToQuestResponseDTO(saveQuest);
    }

    public void deleteQuest(UUID id) {

        Quest quest = findQuestById(id);
        questRepository.delete(quest);
    }

    // =================== Helper methods ===================== //

    private QuestResponseDTO mapToQuestResponseDTO(Quest quest) {
        return QuestResponseDTO.builder()
                .id(quest.getId())
                .title(quest.getTitle())
                .description(quest.getDescription())
                .xpReward(quest.getXpReward())
                .status(quest.getStatus())
                .createdAt(quest.getCreatedAt())
                .updatedAt(quest.getUpdatedAt())
                .completedAt(quest.getCompletedAt())
                .build();
    }

    private Quest findQuestById(UUID id) {
        return questRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quest not found"));
    }

}
