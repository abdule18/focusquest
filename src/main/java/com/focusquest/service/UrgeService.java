package com.focusquest.service;

import com.focusquest.dto.request.UrgeRequestDTO;
import com.focusquest.dto.response.UrgeResponseDTO;
import com.focusquest.exception.ResourceNotFoundException;
import com.focusquest.model.UrgeLog;
import com.focusquest.repositories.UrgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UrgeService {
    private final UrgeRepository urgeRepository;

    public UrgeResponseDTO createUrge(UrgeRequestDTO request) {

        LocalDateTime now = LocalDateTime.now();

        UrgeLog urgeLog = UrgeLog.builder()
                .urgeLevel(request.getUrgeLevel())
                .trigger(request.getTrigger())
                .actionTaken(request.getActionTaken())
                .resisted(request.getResisted())
                .createdAt(now)
                .build();

        UrgeLog savedUrge = urgeRepository.save(urgeLog);

        return  mapToUrgeResponseDTO(savedUrge);
    }

    public List<UrgeResponseDTO> getAllUrges() {

        List<UrgeLog> urges = urgeRepository.findAll();

        List<UrgeResponseDTO> responseList = new ArrayList<>();

        for (UrgeLog urgeLog : urges) {
            UrgeResponseDTO responseDTO = mapToUrgeResponseDTO(urgeLog);
            responseList.add(responseDTO);
        }
        return responseList;
    }

    public UrgeResponseDTO getUrgeById(UUID id) {

        UrgeLog urgeLog = findUrgeById(id);

        return mapToUrgeResponseDTO(urgeLog);
    }

    public void deleteUrge(UUID id) {

        UrgeLog urgeLog = findUrgeById(id);
        urgeRepository.delete(urgeLog);
    }


    // =================== Helper methods ===================== //

    private UrgeResponseDTO mapToUrgeResponseDTO(UrgeLog urgeLog) {

        return UrgeResponseDTO.builder()
                .id(urgeLog.getId())
                .urgeLevel(urgeLog.getUrgeLevel())
                .trigger(urgeLog.getTrigger())
                .actionTaken(urgeLog.getActionTaken())
                .resisted(urgeLog.isResisted())
                .createdAt(urgeLog.getCreatedAt())
                .build();
    }
    private UrgeLog findUrgeById(UUID id)  {

        return urgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Urge not found"));
    }


}
