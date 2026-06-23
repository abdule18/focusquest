package com.focusquest.service;

import com.focusquest.dto.request.RelapseRequestDTO;
import com.focusquest.dto.response.RelapseResponseDTO;
import com.focusquest.exception.ResourceNotFoundException;
import com.focusquest.model.RelapseLog;
import com.focusquest.repositories.RelapseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RelapseService {

    private final RelapseRepository relapseRepository;

    public RelapseResponseDTO createRelapse(RelapseRequestDTO relapse) {

        LocalDateTime now = LocalDateTime.now();

        RelapseLog relapseLog = RelapseLog.builder()
                .trigger(relapse.getTrigger())
                .whatHappened(relapse.getWhatHappened())
                .lessonLearned(relapse.getLessonLearned())
                .preventionPlan(relapse.getPreventionPlan())
                .createdAt(now)
                .build();

        RelapseLog savedRelapse = relapseRepository.save(relapseLog);

        return mapToRelapseResponseDTO(savedRelapse);

    }

    public List<RelapseResponseDTO> getAllRelapse(){

        List<RelapseLog> relapses = relapseRepository.findAll();

        List<RelapseResponseDTO> responseList = new ArrayList<>();

        for (RelapseLog relapse : relapses) {
            RelapseResponseDTO relapseResponseDTO = mapToRelapseResponseDTO(relapse);
            responseList.add(relapseResponseDTO);
        }

        return responseList;
    }

    public RelapseResponseDTO getRelapseById(UUID id) {

        RelapseLog relapse = findRelapseById(id);

        return mapToRelapseResponseDTO(relapse);
    }

    public void deleteRelapse(UUID id) {

        RelapseLog relapseLog = findRelapseById(id);

        relapseRepository.delete(relapseLog);
    }


    // =================== Helper methods ===================== //

    private RelapseResponseDTO mapToRelapseResponseDTO(RelapseLog relapse) {

        return RelapseResponseDTO.builder()
                .id(relapse.getId())
                .trigger(relapse.getTrigger())
                .whatHappened(relapse.getWhatHappened())
                .lessonLearned(relapse.getLessonLearned())
                .preventionPlan(relapse.getPreventionPlan())
                .createdAt(relapse.getCreatedAt())
                .build();
    }

    private RelapseLog findRelapseById(UUID id) {

        return relapseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relapse not found"));

    }
}
