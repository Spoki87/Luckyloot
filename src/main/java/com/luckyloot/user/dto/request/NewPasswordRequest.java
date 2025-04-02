package com.luckyloot.user.dto.request;

import lombok.Value;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class NewPasswordRequest {
    String resetToken;
    String newPassword;
}
