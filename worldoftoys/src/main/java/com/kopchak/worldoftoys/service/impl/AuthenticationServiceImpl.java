package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.dto.UsernameDto;
import com.kopchak.worldoftoys.exception.AccountIsAlreadyActivatedException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.exception.UsernameAlreadyExistException;
import com.kopchak.worldoftoys.model.ConfirmTokenType;
import com.kopchak.worldoftoys.service.AuthenticationService;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import com.kopchak.worldoftoys.service.EmailSenderService;
import com.kopchak.worldoftoys.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getEmail();
        if (userService.isUserRegistered(username)) {
            throw new UsernameAlreadyExistException(HttpStatus.CONFLICT, "Username already exist!");
        }
        userService.registerUser(userRegisterDto);
        var confirmationToken = confirmationTokenService.createConfirmToken(username,
                ConfirmTokenType.ACTIVATION);
        emailSenderService.sendConfirmEmail(username, userRegisterDto.getFirstname(),
                confirmationToken.getToken(), ConfirmTokenType.ACTIVATION);
    }

    @Override
    public void resendVerificationEmail(UsernameDto username) {
        String email = username.getEmail();
        if (!userService.isUserRegistered(email)) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "Username does not exist!");
        }
        if (userService.isUserActivated(email)) {
            throw new AccountIsAlreadyActivatedException(HttpStatus.CONFLICT, "Account is already activated");
        }
        var user = userService.findUserByUsername(email);
        if (!confirmationTokenService.isValidActivationTokenExists(email)) {
            var confirmationToken = confirmationTokenService.createConfirmToken(email,
                    ConfirmTokenType.ACTIVATION);
            emailSenderService.sendConfirmEmail(email, user.getFirstname(), confirmationToken.getToken(),
                    ConfirmTokenType.ACTIVATION);
        }
    }

    @Override
    public void resetPassword(UsernameDto username) {
        String email = username.getEmail();
        if (!userService.isUserRegistered(email)) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "Username does not exist!");
        }
        var user = userService.findUserByUsername(email);
        var confirmationToken = confirmationTokenService.createConfirmToken(email,
                ConfirmTokenType.RESET_PASSWORD);
        emailSenderService.sendConfirmEmail(email, user.getFirstname(), confirmationToken.getToken(),
                ConfirmTokenType.RESET_PASSWORD);
    }

    @Override
    public TokenAuthDto authenticate(UserAuthDto userAuthDto) {
        String username = userAuthDto.getEmail();
        if (!userService.isUserRegistered(username) || !userService.isPasswordValid(userAuthDto)) {
            throw new UserNotFoundException(HttpStatus.UNAUTHORIZED, "Bad user credentials!");
        }
        if (!userService.isUserActivated(username)) {
            throw new UserNotFoundException(HttpStatus.FORBIDDEN, "Account is not activated!");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDto.getEmail(),
                        userAuthDto.getPassword()
                )
        );
        String jwtToken = userService.saveUserAuthToken(userAuthDto.getEmail());
        return TokenAuthDto.builder()
                .token(jwtToken)
                .build();
    }
}
