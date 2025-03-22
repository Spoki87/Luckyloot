package com.luckyloot.user.service;

import com.luckyloot.email.EmailSender;
import com.luckyloot.exception.ResourceNotFoundException;
import com.luckyloot.user.model.ConfirmationToken;
import com.luckyloot.user.model.User;
import com.luckyloot.user.repository.ConfirmationTokenRepository;
import com.luckyloot.user.repository.UserRepository;
import com.luckyloot.utils.HtmlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSender emailSender;
    private final UserRepository userRepository;

    @Value("${app.base-url}")
    private String BASE_URL;


    public void createToken(User user) {

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(user,token);

        confirmationTokenRepository.save(confirmationToken);

        String confirmationLink = String.format("%s/api/user/confirm?token=%s",BASE_URL,token);

        String htmlContent = HtmlUtils.readHtmlContent("templates/confirm_account.html");
        htmlContent = htmlContent.replaceAll("%LINK%", confirmationLink);

        emailSender.send(user.getEmail(), htmlContent,"Confirmation of registration");
    }


    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(()->new ResourceNotFoundException(ConfirmationToken.class, token));

        if(confirmationToken.getConfirmedTime() != null){
            throw new IllegalStateException("Account already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredTime();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            createToken(confirmationToken.getUser());
            confirmationTokenRepository.delete(confirmationToken);
            throw new IllegalStateException("Token expired. New token has been sent to your email");
        }

        confirmationToken.setConfirmedTime();
        userRepository.enableUser(confirmationToken.getUser().getEmail());
    }
}
