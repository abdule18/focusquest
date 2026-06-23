package com.focusquest.controller;

import com.focusquest.dto.request.RelapseRequestDTO;
import com.focusquest.dto.response.RelapseResponseDTO;
import com.focusquest.service.RelapseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/relapses")
public class RelapseController {

    private final RelapseService relapseService;

    @PostMapping
    public ResponseEntity<RelapseResponseDTO> createRelapse(@Valid @RequestBody RelapseRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(relapseService.createRelapse(request));
    }

    @GetMapping
    public ResponseEntity<List<RelapseResponseDTO>> getAllRelapses() {

        List<RelapseResponseDTO> responseDTOS = relapseService.getAllRelapse();

        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelapseResponseDTO> getRelapseById(@PathVariable UUID id) {

        return ResponseEntity.ok(relapseService.getRelapseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelapse(@PathVariable UUID id) {

        relapseService.deleteRelapse(id);
        return ResponseEntity.noContent().build();
    }
}
