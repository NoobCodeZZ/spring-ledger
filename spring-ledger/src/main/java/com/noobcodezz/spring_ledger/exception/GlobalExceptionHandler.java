package com.noobcodezz.spring_ledger.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// In exception/GlobalExceptionHandler.java
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        log.error("Validation failed: {}", ex.getErrors());

        // Join all errors with line breaks for readability
        String errorMessage = String.join("\n", ex.getErrors());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // 400 status
                .body(errorMessage);
    }

    // Keep your other exception handlers...
    @ExceptionHandler(BankNotFoundException.class)
    public ResponseEntity<String> handleBankNotFound(BankNotFoundException ex) {
        log.error("Bank not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        log.error("User not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

