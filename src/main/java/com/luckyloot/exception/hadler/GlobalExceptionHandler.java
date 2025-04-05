package com.luckyloot.exception.hadler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.luckyloot.exception.base.BusinessException;
import com.luckyloot.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<String>> handleBusinessException(BusinessException ex) {
        return buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
        return buildError("Access Denied: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + Objects.requireNonNull(e.getDefaultMessage()))
                .collect(Collectors.joining("; "));
        return buildError(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidEnumValue(InvalidFormatException ex) {
        String message = "Invalid value provided";
        if (ex.getTargetType().isEnum() && !ex.getPath().isEmpty()) {
            String fieldName = ex.getPath().get(0).getFieldName();
            message = String.format("Invalid value '%s' for field '%s'", ex.getValue(), fieldName);
        }
        return buildError(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildError("Duplicate entry error", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleOtherExceptions(Exception ex) {
        System.out.println(ex.getMessage());
        return buildError("Unexpected server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResponse<String>> buildError(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(ApiResponse.error(message, status));
    }
}
