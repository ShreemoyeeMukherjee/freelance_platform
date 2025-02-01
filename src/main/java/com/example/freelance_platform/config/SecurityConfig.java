package com.example.freelance_platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
       return http.authorizeHttpRequests(request->request
        .requestMatchers("/api/user/register","/db-console/**","/","/login").permitAll()

        .requestMatchers("/api/user/hello").authenticated())
       
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
    
      
        
        .build();
        
   
        
        
    }
    @Autowired
    UserDetailsServiceImp userDetailsServiceImp;
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(new BCryptPasswordEncoder()); 
        daoProvider.setUserDetailsService(userDetailsServiceImp);
        return daoProvider;
    }

    
}
