package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.user.UserAuthDto;
import com.kopchak.worldoftoys.dto.user.UserDto;
import com.kopchak.worldoftoys.dto.user.UserRegisterDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.token.AuthTokenType;
import com.kopchak.worldoftoys.model.token.AuthenticationToken;
import com.kopchak.worldoftoys.model.user.Role;
import com.kopchak.worldoftoys.model.user.User;
import com.kopchak.worldoftoys.repository.token.AuthTokenRepository;
import com.kopchak.worldoftoys.repository.user.UserRepository;
import com.kopchak.worldoftoys.service.JwtTokenService;
import com.kopchak.worldoftoys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterDto registerUser(UserRegisterDto userRegisterDto) {
        User user = userRegisterDto.toUser();
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return new UserRegisterDto(user);
    }

    public boolean isUserRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.BAD_REQUEST, "Username does not exist!"));
    }

    public UserDto findUserByUsername(String email) {
        var user = findUserByEmail(email);
        return new UserDto(user);
    }

    public boolean isUserActivated(String email) {
        User user = findUserByEmail(email);
        return user.getEnabled();
    }

    public String saveUserAuthToken(String email) {
        var user = findUserByEmail(email);
        var jwtToken = jwtTokenService.generateAuthToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return jwtToken;
    }

    public boolean isPasswordValid(UserAuthDto userAuthDto) {
        User user = findUserByEmail(userAuthDto.getEmail());
        return passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword());
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = new AuthenticationToken(jwtToken, user, AuthTokenType.BEARER, false, false);
        authTokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = authTokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(authenticationToken -> {
            authenticationToken.setExpired(true);
            authenticationToken.setRevoked(true);
        });
        authTokenRepository.saveAll(validUserTokens);
    }
}
