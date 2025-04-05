package com.luckyloot.user.appuser.dto.request;

import com.luckyloot.user.appuser.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class CreateUserRequest implements Serializable {

    @NotBlank(message = "Username is required. Please provide a valid username")
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters long")
    String username;

    @NotBlank(message = "Email is required. Please provide a valid email address")
    @Email(message = "The email format is invalid. Please provide a valid email")
    String email;

    @NotBlank(message = "Password is required. Please provide a secure password")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    String password;
}
