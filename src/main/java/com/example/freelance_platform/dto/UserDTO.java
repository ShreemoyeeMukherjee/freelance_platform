package com.example.freelance_platform.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String password;

    
}
