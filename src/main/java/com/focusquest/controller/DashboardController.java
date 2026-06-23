package com.focusquest.controller;

import com.focusquest.dto.response.DashboardResponseDTO;
import com.focusquest.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getDashboardStats() {

        DashboardResponseDTO dashboard = dashboardService.getDashboardStats();

        return ResponseEntity.ok(dashboard);
    }
}
