package com.luckyloot.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
    private final String message;
    private final T data;

    public ApiResponse(String message,T data){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }
}
