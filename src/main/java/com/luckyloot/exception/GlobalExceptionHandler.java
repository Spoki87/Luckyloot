package com.luckyloot.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.luckyloot.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalStateException(IllegalStateException ex) {

        ApiResponse<String> response = ApiResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceAlreadyTakenException.class)
    public ResponseEntity<ApiResponse<String>> handleEmailAlreadyTakenException(ResourceAlreadyTakenException ex) {

        ApiResponse<String> response = ApiResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessages.add(Objects.requireNonNull(error.getDefaultMessage()).trim());
        });

        ApiResponse<String> response = ApiResponse.error(errorMessages.toString(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidEnumValue(InvalidFormatException ex) {
        String fieldName = null;
        String message = "Invalid value provided";

        if (ex.getTargetType().isEnum()) {
            fieldName = ex.getPath().get(0).getFieldName();
            message = String.format("Invalid value '%s' for field '%s'", ex.getValue(), fieldName);
        }

        ApiResponse<String> response = ApiResponse.error(message, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Duplicate entry error";

        ApiResponse<String> response = ApiResponse.error(message, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
