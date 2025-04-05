package com.luckyloot.user.appuser.dto.request;

import com.luckyloot.user.appuser.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * DTO for {@link User}
 */
@Value
public class ResetPasswordRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "The email format is invalid. Please provide a valid email")
    String email;
}
