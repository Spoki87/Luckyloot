package com.luckyloot.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class ChangePasswordRequest {
    @NotBlank(message = "Current password is required to change to the new password")
    String currentPassword;

    @NotBlank(message = "New password is required. Please provide a secure password")
    String newPassword;
}
