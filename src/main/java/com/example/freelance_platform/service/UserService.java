package com.example.freelance_platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.freelance_platform.dto.UserDTO;
import com.example.freelance_platform.models.RoleEnum;
import com.example.freelance_platform.models.User;
import com.example.freelance_platform.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    User user;
    @Autowired
    PasswordEncoder passwordEncoder;
    Long id = (long) 0 ;

    public int  CreateUser(   UserDTO user_info , String role)
    {
       try{
        id++;
         String user_provided_email = user_info.getEmail();
         Optional<User>user_with_this_email = userRepository.findByEmail(user_provided_email);
         
         if(user_with_this_email.isPresent() == false){
        user.setEmail(user_info.getEmail());
         }
         else
         {
            throw new Error("Email already exists");
         }
        user.setName(user_info.getName());
        user.setId(id);
        
          String hashedPassword = passwordEncoder.encode(user_info.getPassword());
        user.setPassword(hashedPassword);
        
        
        if(role.equals("CLIENT") == true){
        user.setRole(RoleEnum.CLIENT);
        }
        else{
        user.setRole(RoleEnum.FREELANCER);
        }
        //System.out.println(hashedPassword);
       userRepository.save(user);
        }
        catch(Exception e)
        {
            System.out.printf("Exception occured in registering user"+e.getMessage());
        }
        return(1);

    }
    
}
