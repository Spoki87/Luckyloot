package com.luckyloot.user.security.service;

import com.luckyloot.exception.domain.UserNotFoundException;
import com.luckyloot.user.appuser.dto.request.AuthenticateUserRequest;
import com.luckyloot.user.appuser.dto.response.AuthenticatedUserResponse;
import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.appuser.repository.UserRepository;
import com.luckyloot.user.security.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticatedUserResponse Authenticate(AuthenticateUserRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticatedUserResponse(user.getRole(),jwtToken);
    }
}
