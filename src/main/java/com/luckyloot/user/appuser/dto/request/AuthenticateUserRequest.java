package com.luckyloot.user.appuser.dto.request;

import com.luckyloot.user.appuser.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Valid
@Getter
public class AuthenticateUserRequest implements Serializable {
    @NotBlank(message = "Email is required. Please provide a valid email address")
    @Email(message = "The email format is invalid. Please provide a valid email")
    String email;

    @NotBlank(message = "Password is required")
    String password;
}
