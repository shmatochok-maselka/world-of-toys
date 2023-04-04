package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.ConfirmationToken;
import com.kopchak.worldoftoys.model.TokenType;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.repository.ConfirmationTokenRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public ConfirmationToken createConfirmToken(String username){
        var user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!"));
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, TokenType.CONFIRMATION, user,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        return confirmationToken;
    }
}
