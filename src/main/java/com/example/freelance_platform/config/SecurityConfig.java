package com.example.freelance_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.debug.DebugFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
       return http.authorizeHttpRequests(request->request
        .requestMatchers("api/user/register","/api/user/hello","/db-console/**","/","/login").permitAll()

        .anyRequest().authenticated())
       
        .csrf(AbstractHttpConfigurer::disable)
    //    .formLogin(formLogin->formLogin.permitAll())
    //    .httpBasic(Customizer.withDefaults())
      
        
        .build();
        
        
    }
    
}
