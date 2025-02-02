package com.example.freelance_platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.freelance_platform.config.UserDetailsImp;
import com.example.freelance_platform.dto.UserDTO;
import com.example.freelance_platform.dto.UserLoginDTO;
import com.example.freelance_platform.models.RoleEnum;
import com.example.freelance_platform.models.User;
import com.example.freelance_platform.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    User user;
    private BCryptPasswordEncoder encoder=  new BCryptPasswordEncoder(12);
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
        
          String hashedPassword = encoder.encode(user_info.getPassword());
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
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtService jwtService;

    public String loginUser(UserLoginDTO userLogin) throws Exception
    {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(),userLogin.getPassword()));
        
        
        if(authentication.isAuthenticated() == true)
        {


           
          //Authentication a_uthentication = SecurityContextHolder.getContext().getAuthentication();
          Object principal = authentication.getPrincipal();
          //System.out.println("Principal type: " + principal.getClass().getName());
               User currentUser  = null;
         if(principal instanceof UserDetailsImp)
         {
            UserDetailsImp userDetails = (UserDetailsImp)principal;
            String username = userDetails.getUsername();
            System.out.println(username);
             currentUser = userRepository.findByEmail(username).get(); // findByEmail returns type Optional
            
         }
         else
         {
            System.out.println("No");
         }

         String token = jwtService.generateToken(currentUser);
         System.out.println(token);
         return(token);
       
        }
        else
        {
            throw new Exception("Token is not authenticated");
        }
        
    }
    
}
