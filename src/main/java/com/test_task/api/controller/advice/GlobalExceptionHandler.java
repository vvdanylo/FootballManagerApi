package com.test_task.api.controller.advice;

import com.test_task.api.dto.ExceptionDto;
import com.test_task.api.dto.ValidationExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionDto> handleResponseStatusException(ResponseStatusException ex) {
        final var exceptionDto = ExceptionDto.builder()
                .message(ex.getReason())
                .statusCode(ex.getStatusCode())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final var errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map((e) -> ((FieldError) e).getField() + ": " + e.getDefaultMessage())
                .toList();
        final var exceptionDto = ValidationExceptionDto.builder()
                .errors(errors)
                .statusCode(ex.getStatusCode())
                .build();
        return ResponseEntity.status(exceptionDto.getStatusCode()).body(exceptionDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGeneralException(Exception ex) {
        final var exceptionDto = ExceptionDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occurred: " + ex.getMessage())
                .build();
        return ResponseEntity.status(exceptionDto.getStatusCode()).body(exceptionDto);
    }
}
