package com.example.authservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.authservice.model.UserDto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGenerator{

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Map<String, String> generateToken(UserDto user) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenGenerate = new HashMap<>();
        jwtTokenGenerate.put("token", jwtToken);
        return jwtTokenGenerate;
    }
}
