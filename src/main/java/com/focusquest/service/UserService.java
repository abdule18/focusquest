package com.focusquest.service;

import com.focusquest.dto.request.UserRequestDTO;
import com.focusquest.dto.response.UserResponseDTO;
import com.focusquest.exception.EmailAlreadyExistsException;
import com.focusquest.exception.ResourceNotFoundException;
import com.focusquest.model.User;
import com.focusquest.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;



    public UserResponseDTO createRegister(@Valid UserRequestDTO request) {

        LocalDateTime now = LocalDateTime.now();

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("email is taken");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(encoder.encode(request.getPassword()))
                .createdAt(now)
                .build();

        User savedUser = userRepository.save(user);

        String userToken = jwtService.generateToken(request.getEmail());

        return mapToUserResponseDTO(savedUser, userToken);
    }

    public UserResponseDTO login(@Valid UserRequestDTO request) {

        User user = findUserByEmail(request.getEmail());
        if (!encoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResourceNotFoundException("Invalid login");
        }

        String userToken = jwtService.generateToken(request.getEmail());
        return mapToUserResponseDTO(user, userToken);

    }

    // =================== Helper methods ===================== //

    private UserResponseDTO mapToUserResponseDTO (User user, String token) {

        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(token)
                .createdAt(user.getCreatedAt())
                .build();
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid login"));
    }

}
