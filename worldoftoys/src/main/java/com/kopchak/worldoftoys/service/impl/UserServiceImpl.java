package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.user.*;
import com.kopchak.worldoftoys.exception.IncorrectPasswordException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.token.AuthTokenType;
import com.kopchak.worldoftoys.model.token.AuthenticationToken;
import com.kopchak.worldoftoys.model.user.Role;
import com.kopchak.worldoftoys.model.user.User;
import com.kopchak.worldoftoys.repository.cart.CartItemsRepository;
import com.kopchak.worldoftoys.repository.token.AuthTokenRepository;
import com.kopchak.worldoftoys.repository.user.UserRepository;
import com.kopchak.worldoftoys.service.JwtTokenService;
import com.kopchak.worldoftoys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final CartItemsRepository cartItemsRepository;
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

    public boolean isPasswordsMatch(String username, String password) {
        User user = findUserByEmail(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void updateUser(UserUpdateDto userUpdateDto, Principal principal) {
        User user = findUserByEmail(principal.getName());
        if(!isValidName(userUpdateDto.getFirstname())){
            user.setFirstname(userUpdateDto.getFirstname());
        }
        if(!isValidName(userUpdateDto.getLastname())){
            user.setLastname(userUpdateDto.getLastname());
        }
        userRepository.save(user);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto, Principal principal) {
        User user = findUserByEmail(principal.getName());
        if(!isPasswordsMatch(user.getUsername(), changePasswordDto.getOldPassword())){
            throw new IncorrectPasswordException(HttpStatus.BAD_REQUEST, "Incorrect old password!");
        }
        if(isPasswordsMatch(user.getUsername(), changePasswordDto.getNewPassword())){
            throw new IncorrectPasswordException(HttpStatus.BAD_REQUEST, "New password matches old password!");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(Principal principal) {
        User user = findUserByEmail(principal.getName());
        cartItemsRepository.deleteCartItemsByUserId(user.getId());
        userRepository.delete(user);
    }

    private boolean isValidName(String name){
        return name == null || name.isEmpty() || name.trim().isEmpty();
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
