package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.exception.UsernameAlreadyExistException;
import com.kopchak.worldoftoys.model.Role;
import com.kopchak.worldoftoys.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    String register(UserRegisterDto userRegisterDto);
    TokenAuthDto authenticate(UserAuthDto userAuthDto);
    public String confirmToken(String token);
}
