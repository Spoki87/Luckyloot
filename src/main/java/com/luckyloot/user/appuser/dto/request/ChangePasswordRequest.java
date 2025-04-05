package com.luckyloot.user.appuser.dto.request;

import com.luckyloot.user.appuser.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for {@link User}
 */
@Value
public class ChangePasswordRequest {
    @NotBlank(message = "Current password is required to change to the new password")
    String currentPassword;

    @NotBlank(message = "New password is required. Please provide a secure password")
    String newPassword;
}
