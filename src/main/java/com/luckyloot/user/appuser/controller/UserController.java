package com.luckyloot.user.appuser.controller;

import com.luckyloot.response.ApiResponse;
import com.luckyloot.user.appuser.dto.request.*;
import com.luckyloot.user.appuser.dto.response.AuthenticatedUserResponse;
import com.luckyloot.user.appuser.dto.response.UserResponse;
import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.security.service.AuthenticationService;
import com.luckyloot.user.appuser.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.register(request);
        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }

    @GetMapping("/confirm")
    public ResponseEntity<ApiResponse<String>> confirmRegistration(@RequestParam String token) {
        userService.confirmRegistration(token);
        return ResponseEntity.ok(ApiResponse.success("Account confirmed"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticatedUserResponse>> login(@Valid @RequestBody AuthenticateUserRequest request) {
        AuthenticatedUserResponse authenticatedUserResponse = authenticationService.Authenticate(request);
        return ResponseEntity.ok(ApiResponse.success(authenticatedUserResponse));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody ChangePasswordRequest request){
        userService.changePassword(user,request);
        return ResponseEntity.ok(ApiResponse.success("Password changed"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@Valid @RequestBody ResetPasswordRequest request){
        userService.resetPassword(request);
        return ResponseEntity.ok(ApiResponse.success("Email with link to reset your password has been sent"));
    }

    @PostMapping("/new-password")
    public ResponseEntity<ApiResponse<String>> newPassword(@Valid @RequestBody NewPasswordRequest request){
        userService.setNewPassword(request);
        return ResponseEntity.ok(ApiResponse.success("New password has been set"));
    }

}
