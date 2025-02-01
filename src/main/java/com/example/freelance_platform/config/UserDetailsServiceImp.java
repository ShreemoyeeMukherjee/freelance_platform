package com.example.freelance_platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.freelance_platform.models.User;

import com.example.freelance_platform.repository.UserRepository;
import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

   
    @Autowired
    UserRepository userRepository;
    @Override
        
       public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
             Optional<User>existing_user_optional = userRepository.findByEmail(username);
             if(existing_user_optional.isPresent() == true)
             {
                   User existing_user = existing_user_optional.get();
                   UserDetailsImp retrieved_user=  new UserDetailsImp(existing_user); 
                   return(retrieved_user);
             }
             else
             {
                throw new UsernameNotFoundException("Email not found in database");
             }
        
    
        }
    
}
