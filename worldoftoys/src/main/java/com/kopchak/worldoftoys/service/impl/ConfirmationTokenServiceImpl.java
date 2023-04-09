package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.ConfirmTokenDto;
import com.kopchak.worldoftoys.dto.PasswordResetDto;
import com.kopchak.worldoftoys.exception.ConfirmationTokenExpiredException;
import com.kopchak.worldoftoys.exception.NewPasswordMatchesOldException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.ConfirmTokenType;
import com.kopchak.worldoftoys.model.ConfirmationToken;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.repository.ConfirmationTokenRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmTokenDto createConfirmToken(String username, ConfirmTokenType tokenType) {
        var user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!"));
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, tokenType, user,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        saveConfirmationToken(confirmationToken);
        return new ConfirmTokenDto(confirmationToken);
    }

    public boolean isValidActivationTokenExists(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!"));
        var confirmTokensList = confirmationTokenRepository
                .findAllByUserId(user.getId())
                .stream()
                .filter(confirmToken -> confirmToken.getTokenType() == ConfirmTokenType.ACTIVATION)
                .toList();
        for (ConfirmationToken confirmationToken : confirmTokensList) {
            if (confirmationToken.getExpiresAt().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token);
        setConfirmedAt(token);
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        return "Account activated! You can close this link.";
    }

    @Override
    @Transactional
    public String confirmResetToken(String token, PasswordResetDto newPassword) {
        ConfirmationToken confirmationToken = getToken(token);
        setConfirmedAt(token);
        String email = confirmationToken.getUser().getEmail();
        var user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!"));
        if(passwordEncoder.matches(newPassword.getPassword(), user.getPassword())){
            throw new NewPasswordMatchesOldException(HttpStatus.BAD_REQUEST, "New password matches old password!");
        }
        user.setPassword(passwordEncoder.encode(newPassword.getPassword()));
        return "Password successfully changed!";
    }

    private ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(() ->
                new ConfirmationTokenExpiredException(HttpStatus.FORBIDDEN, "Confirmation link is expired!"));
    }

    private void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
