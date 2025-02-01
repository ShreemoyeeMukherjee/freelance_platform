package com.example.freelance_platform.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import com.example.freelance_platform.models.RoleEnum;

@Entity
@Getter
@Setter
@Component
@NoArgsConstructor

public class User {

   @Id
   //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")   
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;
    
}
