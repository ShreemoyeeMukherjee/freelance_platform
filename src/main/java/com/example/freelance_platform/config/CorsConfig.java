package com.example.freelance_platform.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration

public class CorsConfig  implements  WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
         registry.addMapping("/**")
         .allowedOrigins("http://localhost:3000")
         .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
         .allowedHeaders("*")
         .allowCredentials(true);
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    
}
