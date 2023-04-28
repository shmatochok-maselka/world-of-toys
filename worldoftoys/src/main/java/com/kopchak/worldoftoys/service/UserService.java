package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.user.*;
import com.kopchak.worldoftoys.model.user.Role;

import java.security.Principal;

public interface UserService {
    UserRegisterDto registerUser(UserRegisterDto userRegisterDto);
    boolean isUserRegistered(String email);
    String saveUserAuthToken(String email);
    boolean isUserActivated(String email);
    UserDto findUserByUsername(String email);
    boolean isPasswordsMatch(String username, String password);
    void updateUser(UserUpdateDto userUpdateDto, Principal principal);
    void changePassword(ChangePasswordDto changePasswordDto,Principal principal);
    void deleteAccount(Principal principal);
    boolean hasProperRoleForAuthentication(String email, Role role);
}
