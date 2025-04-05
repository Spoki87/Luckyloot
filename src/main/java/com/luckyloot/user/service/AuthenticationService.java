package com.luckyloot.user.service;

import com.luckyloot.exception.domain.UserNotFoundException;
import com.luckyloot.user.dto.request.AuthenticateUserRequest;
import com.luckyloot.user.dto.response.AuthenticatedUserResponse;
import com.luckyloot.user.model.User;
import com.luckyloot.user.repository.UserRepository;
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
