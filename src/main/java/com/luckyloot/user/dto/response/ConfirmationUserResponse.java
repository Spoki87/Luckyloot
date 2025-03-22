package com.luckyloot.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ConfirmationUserResponse {
    private LocalDateTime confirmedTime;
}
