package com.focusquest.controller;

import com.focusquest.dto.request.CheckInRequestDTO;
import com.focusquest.dto.response.CheckInResponseDTO;
import com.focusquest.model.DailyCheckIn;
import com.focusquest.service.CheckInService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/check-ins")
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInResponseDTO> createCheckIn(@Valid @RequestBody CheckInRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(checkInService.createCheckIn(request));
    }

    @GetMapping
    public ResponseEntity<List<CheckInResponseDTO>>  getAllCheckIn() {

        List<CheckInResponseDTO> checkIns = checkInService.getAllCheckIns();
        return ResponseEntity.ok(checkIns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckInResponseDTO> getCheckInById(@PathVariable UUID id) {
        return ResponseEntity.ok(checkInService.getCheckInById(id));
    }

    @GetMapping("/today")
    public ResponseEntity<List<CheckInResponseDTO>> getTodayCheckIns() {

        // call service
        List<CheckInResponseDTO> checkIns = checkInService.getTodayCheckIns();
        // return 200 OK
        return ResponseEntity.ok(checkIns);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable UUID id) {

        checkInService.deleteCheckIn(id);
        return ResponseEntity.noContent().build();
    }
}
