package com.example.freelance_platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.freelance_platform.dto.UserDTO;
import com.example.freelance_platform.models.User;
import com.example.freelance_platform.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    User user;
    public int  CreateUser(   UserDTO user_info , String role)
    {
        try{
         String user_provided_email = user_info.getEmail();
         Optional<User>user_with_this_email = userRepository.findByEmail(user_provided_email);
        //  if(user_with_this_email.isPresent() == true){
        user.setEmail(user_info.getEmail());
        //  }
        //  else
        //  {
        //     throw new Error("Email already exists");
        //  }
        user.setName(user_info.getName());
        user.setPassword(user_info.getPassword());
        System.out.println(userRepository.save(user));
        
        }
        catch(Exception e)
        {
            //System.out.println("Exception occured in registering user",e.getMessage());
        }
        return(1);

    }
    
}
