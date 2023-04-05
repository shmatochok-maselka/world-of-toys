package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;

public interface AuthenticationService {
    void register(UserRegisterDto userRegisterDto);
    TokenAuthDto authenticate(UserAuthDto userAuthDto);
    void resendVerificationEmail(String email);
}
