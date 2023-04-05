package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.ConfirmTokenDto;
import com.kopchak.worldoftoys.exception.AccountIsAlreadyActivatedException;
import com.kopchak.worldoftoys.exception.ConfirmationTokenExpiredException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.ConfirmationToken;
import com.kopchak.worldoftoys.model.TokenType;
import com.kopchak.worldoftoys.repository.ConfirmationTokenRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import com.kopchak.worldoftoys.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    private void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmTokenDto createConfirmToken(String username) {
        var user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!"));
        if (user.getEnabled()) {
            throw new AccountIsAlreadyActivatedException(HttpStatus.CONFLICT, "Account is already activated!");
        }
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, TokenType.CONFIRMATION, user,
//                LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(1));
        saveConfirmationToken(confirmationToken);
        return new ConfirmTokenDto(confirmationToken);
    }

    public boolean isValidTokenInTheList(Integer userId) {
        var confirmTokensList = confirmationTokenRepository.findAllByUserId(userId);
        if (!confirmTokensList.isEmpty()) {
            for (ConfirmationToken confirmationToken : confirmTokensList) {
                if (confirmationToken.getConfirmedAt() != null ||
                        confirmationToken.getExpiresAt().isAfter(LocalDateTime.now())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token);
        setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "Account activated! You can close this link.";
    }

    private ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(() ->
                new ConfirmationTokenExpiredException(HttpStatus.FORBIDDEN, "Confirmation link is expired!"));
    }

    private void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
