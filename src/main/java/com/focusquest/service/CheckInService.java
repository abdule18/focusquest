package com.focusquest.service;

import com.focusquest.dto.request.CheckInRequestDTO;
import com.focusquest.dto.response.CheckInResponseDTO;
import com.focusquest.model.DailyCheckIn;
import com.focusquest.repositories.CheckInRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
