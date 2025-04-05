package com.luckyloot.user.resetpasswordtoken.service;

import com.luckyloot.email.EmailSender;
import com.luckyloot.exception.domain.ResourceNotFoundException;
import com.luckyloot.user.resetpasswordtoken.model.ResetPasswordToken;
import com.luckyloot.user.appuser.model.User;
import com.luckyloot.user.resetpasswordtoken.repository.ResetPasswordTokenRepository;
import com.luckyloot.user.appuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetPasswordTokenService {
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final UserRepository userRepository;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;


    @Value("${app.base-url}")
    private String BASE_URL;

    public void createToken(User user) {

        String token = UUID.randomUUID().toString();

        ResetPasswordToken resetPasswordToken = new ResetPasswordToken(user, token);
        resetPasswordTokenRepository.save(resetPasswordToken);

        String resetPasswordLink = String.format("%s/reset-password?token=%s", BASE_URL, token);

        Context context = new Context();
        context.setVariable("resetPasswordLink", resetPasswordLink);

        String htmlContent = templateEngine.process("reset_password", context);

        emailSender.send(user.getEmail(), htmlContent, "Password reset");
    }

    public User useToken(String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException(ResetPasswordToken.class, token));

        if (resetPasswordToken.getUsedTime() != null) {
            throw new IllegalStateException("Password has been changed using this token");
        }

        LocalDateTime expiredAt = resetPasswordToken.getExpiredTime();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        resetPasswordToken.setUsedTime(LocalDateTime.now());
        resetPasswordTokenRepository.save(resetPasswordToken);

        return resetPasswordToken.getUser();
    }

}
