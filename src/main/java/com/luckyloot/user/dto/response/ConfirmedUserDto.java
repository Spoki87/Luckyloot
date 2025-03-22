package com.luckyloot.user.dto.response;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class ConfirmedUserDto {
    LocalDateTime confirmedTime;
}
