package com.luckyloot.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class NewPasswordRequest {
    @NotBlank(message = "Reset password token is required")
    String resetToken;

    @NotBlank(message = "Password is required. Please provide a secure password")
    String newPassword;
}
