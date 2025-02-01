package com.example.freelance_platform.service;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.freelance_platform.models.User;

import io.jsonwebtoken.Jwts;
import java.lang.Object;

@Service
public class JwtService {



    public String generateToken(User currentUser)
    {

        

        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        String token = Jwts.builder().signWith(secretKey).subject(currentUser.getEmail()).claim("currentUser",(Object)currentUser).compact();
        return(token);

    }
    
}
