package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.token.TokenAuthDto;
import com.kopchak.worldoftoys.dto.user.UserAuthDto;
import com.kopchak.worldoftoys.dto.user.UserRegisterDto;
import com.kopchak.worldoftoys.dto.user.UsernameDto;
import com.kopchak.worldoftoys.model.user.Role;

public interface AuthenticationService {
    void register(UserRegisterDto userRegisterDto);
    TokenAuthDto authenticate(UserAuthDto userAuthDto, Role role);
    void resendVerificationEmail(UsernameDto username);
    void resetPassword(UsernameDto email);
}
