package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.exception.ConfirmationTokenExpiredException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.exception.UsernameAlreadyExistException;
import com.kopchak.worldoftoys.model.*;
import com.kopchak.worldoftoys.repository.TokenRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        if (userService.isUserRegistered(userRegisterDto.getEmail())) {
            throw new UsernameAlreadyExistException(HttpStatus.BAD_REQUEST, "Username already exist!");
        }
        userService.registerUser(userRegisterDto);
        var confirmationToken = confirmationTokenService.createConfirmToken(userRegisterDto.getEmail());
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        emailSenderService.sendConfirmEmail(userRegisterDto.getEmail(), userRegisterDto.getFirstname(),
                confirmationToken.getToken());
    }

    @Override
    public TokenAuthDto authenticate(UserAuthDto userAuthDto) {
        if (!userService.isUserRegistered(userAuthDto.getEmail())) {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDto.getEmail(),
                        userAuthDto.getPassword()
                )
        );
        String jwtToken = userService.saveUserAuthToken(userAuthDto.getEmail());
//        var user = userRepository.findByEmail(userAuthDto.getEmail()).orElseThrow();
//        var jwtToken = jwtTokenService.generateToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
        return TokenAuthDto.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
                new ConfirmationTokenExpiredException(HttpStatus.FORBIDDEN, "Confirmation link is expired!"));
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "Account activated! You can close this link.";
    }
}
