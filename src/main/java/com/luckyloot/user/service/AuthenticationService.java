package com.luckyloot.user.service;

import com.luckyloot.exception.UserNotFoundException;
import com.luckyloot.user.dto.request.AuthenticateUserDto;
import com.luckyloot.user.dto.response.AuthenticatedUserDto;
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

    public AuthenticatedUserDto Authenticate(AuthenticateUserDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticatedUserDto(user.getRole(),jwtToken);
    }
}
