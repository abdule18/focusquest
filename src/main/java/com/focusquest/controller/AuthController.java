package com.focusquest.controller;

import com.focusquest.dto.request.UserRequestDTO;
import com.focusquest.dto.response.UserResponseDTO;
import com.focusquest.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO response = userService.login(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createRegister(@Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO response = userService.createRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
