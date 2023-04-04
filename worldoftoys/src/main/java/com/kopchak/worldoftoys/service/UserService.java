package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.UserRegisterDto;

public interface UserService {
    UserRegisterDto registerUser(UserRegisterDto userRegisterDto);
    boolean isUserRegistered(String email);
    void enableUser(String email);
    String saveUserAuthToken(String email);
}
