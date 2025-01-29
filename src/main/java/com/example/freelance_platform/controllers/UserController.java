package com.example.freelance_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.freelance_platform.dto.UserDTO;
import com.example.freelance_platform.repository.UserRepository;
import com.example.freelance_platform.service.UserService;

@RestController
@RequestMapping("/api/user")


public class UserController {
    @Autowired
    UserDTO userDTO;
    
    @Autowired
    UserService userService;
    @PostMapping("/register")
   
    public ResponseEntity<String> RegisterUser(@RequestBody  UserDTO userDTO, @RequestParam String role)
    {
        userService.CreateUser(userDTO, role);
        return(new ResponseEntity<>("User created ",HttpStatus.OK));
    } 
    
}
