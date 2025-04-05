package com.luckyloot.user.appuser.dto.response;

import com.luckyloot.user.appuser.model.Role;
import com.luckyloot.user.appuser.model.User;
import lombok.Value;

/**
 * DTO for {@link User}
 */
@Value
public class AuthenticatedUserResponse {
    Role role;
    String token;
}
