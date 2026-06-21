package com.focusquest.service;

import com.focusquest.dto.request.CheckInRequestDTO;
import com.focusquest.dto.response.CheckInResponseDTO;
import com.focusquest.model.DailyCheckIn;
import com.focusquest.repositories.CheckInRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;

    public CheckInResponseDTO createCheckIn(CheckInRequestDTO request) {

        LocalDateTime now = LocalDateTime.now();

        DailyCheckIn dailyCheckIn = DailyCheckIn.builder()
                .moodLevel(request.getMoodLevel())
                .urgeLevel(request.getUrgeLevel())
                .hadUrge(request.getHadUrge())
                .relapsed(request.getRelapsed())
                .reflection(request.getReflection())
                .createdAt(now)
                .build();


        DailyCheckIn savedCheckIn = checkInRepository.save(dailyCheckIn);

        return  mapToDailyCheckInResponseDTO(savedCheckIn);
    }

    public List<CheckInResponseDTO> getAllCheckIns() {
        List<DailyCheckIn> checkIn = checkInRepository.findAll();

        return checkIn
                .stream()
                .map(this::mapToDailyCheckInResponseDTO)
                .toList();
    }

    public CheckInResponseDTO getCheckInById(UUID id) {

        DailyCheckIn checkIn = findCheckInById(id);

        return mapToDailyCheckInResponseDTO(checkIn);
    }


    // =================== Helper methods ===================== //

    private CheckInResponseDTO mapToDailyCheckInResponseDTO(DailyCheckIn dailyCheckIn) {

        return CheckInResponseDTO.builder()
                .id(dailyCheckIn.getId())
                .moodLevel(dailyCheckIn.getMoodLevel())
                .urgeLevel(dailyCheckIn.getUrgeLevel())
                .hadUrge(dailyCheckIn.isHadUrge())
                .relapsed(dailyCheckIn.isRelapsed())
                .reflection(dailyCheckIn.getReflection())
                .createdAt(dailyCheckIn.getCreatedAt())
                .build();
    }


    private DailyCheckIn findCheckInById(UUID id) {
        return checkInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check—in not found"));
    }
}
