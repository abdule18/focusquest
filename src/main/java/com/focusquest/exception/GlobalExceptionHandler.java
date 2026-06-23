package com.focusquest.exception;

import com.focusquest.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new LinkedHashMap<>();

        Map<String, String> fieldErrors = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        if (fieldErrors.containsKey("title")) {
            errors.put("title", fieldErrors.get("title"));
        }

        if (fieldErrors.containsKey("description")) {
            errors.put("description", fieldErrors.get("description"));
        }

        if (fieldErrors.containsKey("xpReward")) {
            errors.put("xpReward", fieldErrors.get("xpReward"));
        }

        if (fieldErrors.containsKey("moodLevel")) {
            errors.put("moodLevel", fieldErrors.get("moodLevel"));
        }

        if (fieldErrors.containsKey("urgeLevel")) {
            errors.put("urgeLevel", fieldErrors.get("urgeLevel"));
        }

        if (fieldErrors.containsKey("hadUrge")) {
            errors.put("hadUrge", fieldErrors.get("hadUrge"));
        }

        if (fieldErrors.containsKey("relapsed")) {
            errors.put("relapsed", fieldErrors.get("relapsed"));
        }

        if (fieldErrors.containsKey("reflection")) {
            errors.put("reflection", fieldErrors.get("reflection"));
        }

        // UrgeLog validation fields
        if (fieldErrors.containsKey("trigger")) {
            errors.put("trigger", fieldErrors.get("trigger"));
        }

        if (fieldErrors.containsKey("actionTaken")) {
            errors.put("actionTaken", fieldErrors.get("actionTaken"));
        }

        if (fieldErrors.containsKey("resisted")) {
            errors.put("resisted", fieldErrors.get("resisted"));
        }

        // RelapseLog validation fields
        if (fieldErrors.containsKey("whatHappened")) {
            errors.put("whatHappened", fieldErrors.get("whatHappened"));
        }

        if (fieldErrors.containsKey("lessonLearned")) {
            errors.put("lessonLearned", fieldErrors.get("lessonLearned"));
        }

        if (fieldErrors.containsKey("preventionPlan")) {
            errors.put("preventionPlan", fieldErrors.get("preventionPlan"));
        }

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}