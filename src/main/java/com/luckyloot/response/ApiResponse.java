package com.luckyloot.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;
    private final int status;

    public ApiResponse(String message, T data, HttpStatus httpStatus){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.status = httpStatus.value();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", data, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return new ApiResponse<>(message, null, status);
    }

}
