package com.focusquest.service;

import com.focusquest.dto.request.CheckInRequestDTO;
import com.focusquest.dto.response.CheckInResponseDTO;
import com.focusquest.exception.ResourceNotFoundException;
import com.focusquest.model.DailyCheckIn;
import com.focusquest.repositories.CheckInRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<CheckInResponseDTO> getTodayCheckIns() {

        // 1. Get today
        LocalDate today = LocalDate.now();
        // 2. Create startOfToday
        LocalDateTime startOfToday = today.atStartOfDay();
        // 3. Create startOfTomorrow
        LocalDateTime startOfTomorrow = today.plusDays(1).atStartOfDay();
        // 4. Ask repository for check-ins between those times
        List<DailyCheckIn> checkIns = checkInRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(startOfToday, startOfTomorrow);
        // 5. Create empty response list
        List<CheckInResponseDTO> responseList = new ArrayList<>();
        // 6. Loop through check-ins, map each one, add to response list
        for (DailyCheckIn checkIn : checkIns) {
            CheckInResponseDTO response = mapToDailyCheckInResponseDTO(checkIn);
            responseList.add(response);
        }
        // 7. Return response list
        return responseList;
    }

    public void deleteCheckIn(UUID id) {

        DailyCheckIn checkIn = findCheckInById(id);
        checkInRepository.delete(checkIn);
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
                .orElseThrow(() -> new ResourceNotFoundException("Check—in not found"));
    }
}
