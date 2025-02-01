package com.example.freelance_platform.config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//import org.springframework.security.access.method.P;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.freelance_platform.models.User;

@Service
public class UserDetailsImp  implements UserDetails{
   
    // private String email;
    // private String password;
    // private String name;
    // private String role;
    User user;
    private String role;

    UserDetailsImp(User user){
        this.user=  user;


    }
    @Override
    public  String getPassword()
    {
        return(user.getPassword());
    }
    @Override
    public String getUsername()
    {
        return(user.getEmail());
    }
    public String getName()
    {
        return(user.getName());
    }
    public Long getId()
    {
         return(user.getId());
    }
    public  void setRole()
    {
        if(user.getRole().name() == "CLIENT")
        {
            role =  "CLIENT";
        }
        else
        {
            role =  "FREELANCER";
        }

    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        setRole();
       return(Collections.singleton(new SimpleGrantedAuthority(role)));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }
    @Override
    public boolean isEnabled() {
       
        return true;
       // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
    
}
