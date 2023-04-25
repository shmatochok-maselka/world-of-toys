package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.token.TokenAuthDto;
import com.kopchak.worldoftoys.dto.user.UserAuthDto;
import com.kopchak.worldoftoys.dto.user.UserRegisterDto;
import com.kopchak.worldoftoys.dto.user.UsernameDto;

public interface AuthenticationService {
    void register(UserRegisterDto userRegisterDto);
    TokenAuthDto authenticate(UserAuthDto userAuthDto);
    void resendVerificationEmail(UsernameDto username);
    void resetPassword(UsernameDto email);
}
