package com.luckyloot.user.appuser.dto.request;

import com.luckyloot.user.appuser.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for {@link User}
 */
@Value
public class NewPasswordRequest {
    @NotBlank(message = "Reset password token is required")
    String resetToken;

    @NotBlank(message = "Password is required. Please provide a secure password")
    String newPassword;
}
