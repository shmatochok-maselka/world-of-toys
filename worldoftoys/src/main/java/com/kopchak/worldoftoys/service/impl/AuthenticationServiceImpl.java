package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.exception.UsernameAlreadyExistException;
import com.kopchak.worldoftoys.model.Role;
import com.kopchak.worldoftoys.model.Token;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.repository.TokenRepository;
import com.kopchak.worldoftoys.model.TokenType;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenServiceImpl jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    public TokenAuthDto register(UserRegisterDto userRegisterDto) {
        if (isUserRegistered(userRegisterDto.getEmail())) {
            throw  new UsernameAlreadyExistException(HttpStatus.BAD_REQUEST, "Username already exist!");
        }
        User user = userRegisterDto.toUser();
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(Role.USER);
        var savedUser = userRepository.save(user);
        var jwtToken = jwtTokenService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return TokenAuthDto.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public TokenAuthDto authenticate(UserAuthDto userAuthDto) {
        if(!isUserRegistered(userAuthDto.getEmail())){
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDto.getEmail(),
                        userAuthDto.getPassword()
                )
        );
        var user = userRepository.findByEmail(userAuthDto.getEmail()).orElseThrow();
        var jwtToken = jwtTokenService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return TokenAuthDto.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private boolean isUserRegistered(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
