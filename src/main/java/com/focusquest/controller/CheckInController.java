package com.focusquest.controller;

import com.focusquest.dto.request.CheckInRequestDTO;
import com.focusquest.dto.response.CheckInResponseDTO;
import com.focusquest.service.CheckInService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/check-ins")
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInResponseDTO> createCheckIn(@Valid @RequestBody CheckInRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(checkInService.createCheckIn(request));
    }
}
