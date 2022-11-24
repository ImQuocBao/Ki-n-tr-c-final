package com.example.authservice.config;


import java.util.Map;

import com.example.authservice.model.UserDto;

public interface JwtGenerator {
    Map<String, String> generateToken(UserDto user);
}
