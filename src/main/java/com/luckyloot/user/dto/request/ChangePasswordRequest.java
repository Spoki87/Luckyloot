package com.luckyloot.user.dto.request;

import lombok.Value;

/**
 * DTO for {@link com.luckyloot.user.model.User}
 */
@Value
public class ChangePasswordRequest {
    String currentPassword;
    String newPassword;
}
