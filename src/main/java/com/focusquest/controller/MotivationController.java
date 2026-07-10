package com.focusquest.controller;

import com.focusquest.dto.response.MotivationQuoteResponseDTO;
import com.focusquest.service.MotivationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/motivation")
@AllArgsConstructor
public class MotivationController {

    private final MotivationService motivationService;

    @GetMapping("/daily")
    public ResponseEntity<MotivationQuoteResponseDTO> getDailyMotivationQuote() {

        return ResponseEntity.ok(motivationService.getDailyMotivationQuote());

    }
}
