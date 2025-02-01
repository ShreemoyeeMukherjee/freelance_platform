package com.example.freelance_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.freelance_platform.dto.UserDTO;
import com.example.freelance_platform.dto.UserLoginDTO;
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
             try{
                
        System.out.println(userDTO);
        userService.CreateUser(userDTO, role);
        return(new ResponseEntity<>("User created ",HttpStatus.OK));
             }
             catch( Exception e)
             {
                System.out.println("Error while creating user"+ e.getMessage());
                return(new ResponseEntity<>("User not  created ",HttpStatus.BAD_REQUEST));
             }
    }
    @GetMapping("/hello") 
    public String hello()
    {
        return("Hello");
    }
    @Autowired
    UserLoginDTO userLoginDTO;
    @Autowired
    AuthenticationManager authManager;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLogin) throws Exception
    {
        String ans = userService.loginUser(userLogin);
        return(new ResponseEntity<>("Token created"+ans,HttpStatus.OK));
    }
    
}
