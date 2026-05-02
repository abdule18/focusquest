package com.focusquest.controller;

import com.focusquest.dto.request.QuestRequestDTO;
import com.focusquest.dto.response.QuestResponseDTO;
import com.focusquest.service.QuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quests")
@AllArgsConstructor
public class QuestController {

    private final QuestService questService;

    @PostMapping
    public ResponseEntity<QuestResponseDTO> createQuest(@RequestBody QuestRequestDTO request) {

        QuestResponseDTO response = questService.createQuest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<QuestResponseDTO>> getAllQuests() {
        List<QuestResponseDTO> quests = questService.getAllQuests();
        return ResponseEntity.ok(quests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestResponseDTO> getQuestById(@PathVariable UUID id) {

        return ResponseEntity.ok(questService.getQuestById(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<QuestResponseDTO> completeQuest(@PathVariable UUID id) {
        return ResponseEntity.ok(questService.completeQuest(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuest(@PathVariable UUID id) {

        questService.deleteQuest(id);

        return ResponseEntity.noContent().build();
    }
}
