package com.example.freelance_platform.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.freelance_platform.service.JwtService;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.freelance_platform.config.UserDetailsServiceImp;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@Component
public class JwtFilter extends OncePerRequestFilter{

    
    @Autowired 
    JwtService jwtService;
    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                String authHeader=  request.getHeader("Authorization");
                String token = null;
                String username = null;
                if(authHeader == null || !authHeader.startsWith("Bearer"))
                {
                    try {
                        throw new Exception("JWT Token not found or prefix is invaid");
                    } catch (Exception e) {
                    
                        e.printStackTrace();
                    }
                }
                else
                {
                    token = authHeader.substring(7);
                    try {
                        username = jwtService.extractUsername(token);
                        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
                        {

                            UserDetails userDetails = context.getBean(UserDetailsServiceImp.class).loadUserByUsername(username);
                            System.out.println("Injwtfilter username  "+userDetails.getUsername());
                            if(jwtService.validateToken(token, userDetails) == true)
                            {
                                System.out.println("Jwt is verified");
                                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                               System.out.println("Authtoken "+authtoken);
                                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(authtoken);
                            }
                        }
                    } catch (Exception e) {
                        
                        e.printStackTrace();
                    }
                    
                    

                }
                filterChain.doFilter(request, response);

                
    
    }
    
}
