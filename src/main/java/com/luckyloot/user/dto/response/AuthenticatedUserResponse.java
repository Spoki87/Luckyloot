package com.luckyloot.user.dto.response;

import com.luckyloot.user.model.Role;
import lombok.Value;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class AuthenticatedUserResponse {
    Role role;
    String token;
}
