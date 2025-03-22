package com.luckyloot.user.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest{

    @NotBlank(message = "username is required")
    @Size(min = 8, message = "Username must be at least 8 characters long")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
