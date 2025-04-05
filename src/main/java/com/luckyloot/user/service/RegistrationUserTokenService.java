package com.luckyloot.user.service;

import com.luckyloot.email.EmailSender;
import com.luckyloot.exception.domain.ResourceNotFoundException;
import com.luckyloot.user.model.RegistrationUserToken;
import com.luckyloot.user.model.User;
import com.luckyloot.user.repository.RegistrationUserTokenRepository;
import com.luckyloot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationUserTokenService {

    private final RegistrationUserTokenRepository registrationUserTokenRepository;
    private final EmailSender emailSender;
    private final UserRepository userRepository;
    private final TemplateEngine templateEngine;

    @Value("${app.base-url}")
    private String BASE_URL;

    public void createToken(User user) {

        String token = UUID.randomUUID().toString();

        RegistrationUserToken registrationUserToken = new RegistrationUserToken(user,token);

        registrationUserTokenRepository.save(registrationUserToken);

        String confirmationLink = String.format("%s/api/user/confirm?token=%s",BASE_URL,token);

        Context context = new Context();
        context.setVariable("confirmationLink", confirmationLink);

        String htmlContent = templateEngine.process("confirm_account", context);

        emailSender.send(user.getEmail(), htmlContent,"Confirmation of registration");
    }


    public void confirmToken(String token) {
        RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token)
                .orElseThrow(()->new ResourceNotFoundException(RegistrationUserToken.class, token));

        if(registrationUserToken.getConfirmedTime() != null){
            throw new IllegalStateException("Account already confirmed");
        }

        LocalDateTime expiredAt = registrationUserToken.getExpiredTime();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            createToken(registrationUserToken.getUser());
            registrationUserTokenRepository.delete(registrationUserToken);
            throw new IllegalStateException("Token expired. New token has been sent to your email");
        }

        registrationUserToken.setConfirmedTime();
        userRepository.enableUser(registrationUserToken.getUser().getEmail());
    }
}
