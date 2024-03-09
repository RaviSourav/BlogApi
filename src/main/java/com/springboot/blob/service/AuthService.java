package com.springboot.blob.service;

import com.springboot.blob.payload.LoginDto;
import com.springboot.blob.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}