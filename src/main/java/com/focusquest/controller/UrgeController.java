package com.focusquest.controller;


import com.focusquest.dto.request.UrgeRequestDTO;
import com.focusquest.dto.response.UrgeResponseDTO;
import com.focusquest.service.UrgeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/urges")
public class UrgeController {

    private final UrgeService urgeService;

    @PostMapping
    public ResponseEntity<UrgeResponseDTO> createUrge(@Valid @RequestBody UrgeRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urgeService.createUrge(request));
    }

    @GetMapping
     public ResponseEntity<List<UrgeResponseDTO>> getAllUrges() {

        List<UrgeResponseDTO> urges = urgeService.getAllUrges();

        return ResponseEntity.ok(urges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrgeResponseDTO> getUrgeById(@PathVariable UUID id) {

        return ResponseEntity.ok(urgeService.getUrgeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrge(@PathVariable UUID id) {

        urgeService.deleteUrge(id);
        return ResponseEntity.noContent().build();
    }

}
