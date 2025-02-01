package com.example.freelance_platform.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserLoginDTO {
    private String email;
    private String password;
    
}
