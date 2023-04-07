package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.UserDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;

public interface UserService {
    UserRegisterDto registerUser(UserRegisterDto userRegisterDto);
    boolean isUserRegistered(String email);
    void enableUser(String email);
    String saveUserAuthToken(String email);
    boolean isUserActivated(String email);
    UserDto findUserByUsername(String email);
    void updatePassword(String email, String newPassword);
}
