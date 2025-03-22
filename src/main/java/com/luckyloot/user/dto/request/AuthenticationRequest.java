package com.luckyloot.user.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    String password;
}
