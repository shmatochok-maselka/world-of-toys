package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;

public interface UserService {
    UserRegisterDto registerUser(UserRegisterDto userRegisterDto);
    boolean isUserRegistered(String email);
    String saveUserAuthToken(String email);
    boolean isUserActivated(String email);
    UserDto findUserByUsername(String email);
    boolean isPasswordValid(UserAuthDto userAuthDto);
}
