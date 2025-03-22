package com.luckyloot.user.dto.response;

import com.luckyloot.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private final Role role;
    private String token;
}
