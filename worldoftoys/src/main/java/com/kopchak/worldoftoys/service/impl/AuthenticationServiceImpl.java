package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.exception.UsernameAlreadyExistException;
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
        if (userService.isUserRegistered(userRegisterDto.getEmail())) {
            throw new UsernameAlreadyExistException(HttpStatus.BAD_REQUEST, "Username already exist!");
        }
        userService.registerUser(userRegisterDto);
        var confirmationToken = confirmationTokenService.createConfirmToken(userRegisterDto.getEmail());
        emailSenderService.sendConfirmEmail(userRegisterDto.getEmail(), userRegisterDto.getFirstname(),
                    confirmationToken.getToken());
    }

    public void resendVerificationEmail(String email){
        if (!userService.isUserRegistered(email)) {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!");
        }
        var user = userService.findUserByUsername(email);
        if(!confirmationTokenService.isValidTokenInTheList(user.getId())){
            var confirmationToken = confirmationTokenService.createConfirmToken(email);
            emailSenderService.sendConfirmEmail(email, user.getFirstname(), confirmationToken.getToken());
        }
    }
    @Override
    public TokenAuthDto authenticate(UserAuthDto userAuthDto) {
        String username = userAuthDto.getEmail();
        if (!userService.isUserRegistered(username)) {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!");
        }
        if(!userService.isUserActivated(username)){
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
